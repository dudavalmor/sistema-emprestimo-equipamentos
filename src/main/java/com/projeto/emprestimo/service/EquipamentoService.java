package com.projeto.emprestimo.service;

import com.projeto.emprestimo.dto.EquipamentoRequest;
import com.projeto.emprestimo.exception.ConflitoException;
import com.projeto.emprestimo.model.Equipamento;
import com.projeto.emprestimo.model.TipoEquipamento;
import com.projeto.emprestimo.repository.EmprestimoRepository;
import com.projeto.emprestimo.repository.EquipamentoRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class EquipamentoService {

    private final EquipamentoRepository repository;
    private final EmprestimoRepository emprestimoRepository;

    public EquipamentoService(EquipamentoRepository repository, EmprestimoRepository emprestimoRepository) {
        this.repository = repository;
        this.emprestimoRepository = emprestimoRepository;
    }

    public Equipamento buscar(Long id){
        return repository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Equipamento não encontrado"));
    }

    public Equipamento cadastrar(EquipamentoRequest dados){
        if(repository.existsByPatrimonio(dados.patrimonio())){
            throw new ConflitoException("Patrimônio já cadastrado");
        }

        Equipamento equipamento = new Equipamento();
        equipamento.setPatrimonio(dados.patrimonio());
        equipamento.setNome(dados.nome());
        equipamento.setTipo(dados.tipo());
        equipamento.setDisponivel(true);
        return repository.save(equipamento);
    }

    public Equipamento atualizar(Long id, Equipamento dados){
        Equipamento equipamento = buscar(id);
        equipamento.setNome(dados.getNome());
        equipamento.setTipo(dados.getTipo());
        return repository.save(equipamento);
    }

    public void remover(Long id){
        Equipamento equipamento = buscar(id);
        if(emprestimoRepository.existsByEquipamentoId(id)){
            throw new ConflitoException("Equipamento tem empréstimos registrados");
        }

        repository.delete(equipamento);
    }

    public List<Equipamento> listar(TipoEquipamento tipo, Boolean disponivel){
        if(tipo != null && disponivel != null){
            return repository.findByTipoAndDisponivel(tipo, disponivel);
        }

        if(tipo != null){
            return repository.findByTipo(tipo);
        }

        if(disponivel != null){
            return repository.findByDisponivel(disponivel);
        }

        return repository.findAll();
    }

}
