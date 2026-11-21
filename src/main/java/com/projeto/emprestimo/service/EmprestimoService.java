package com.projeto.emprestimo.service;

import com.projeto.emprestimo.dto.EmprestimoRequest;
import com.projeto.emprestimo.exception.ConflitoException;
import com.projeto.emprestimo.exception.NaoEncontradoException;
import com.projeto.emprestimo.model.Colaborador;
import com.projeto.emprestimo.model.Emprestimo;
import com.projeto.emprestimo.model.Equipamento;
import com.projeto.emprestimo.repository.EmprestimoRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Service
public class EmprestimoService {

    private final EmprestimoRepository repository;
    private final EquipamentoService equipamentoService;
    private final ColaboradorService colaboradorService;

    public EmprestimoService(EmprestimoRepository repository, EquipamentoService equipamentoService, ColaboradorService colaboradorService) {
        this.repository = repository;
        this.equipamentoService = equipamentoService;
        this.colaboradorService = colaboradorService;
    }

    public Emprestimo buscar(Long id){
        return repository.findById(id).orElseThrow(() -> new NaoEncontradoException("Empréstimo não encontrado"));
    }

    public List<Emprestimo> listarDoColaborador(Long colaboradorId){
        colaboradorService.buscar(colaboradorId);
        return repository.findByColaboradorId(colaboradorId);
    }

    @Transactional
    public Emprestimo emprestar(EmprestimoRequest pedido){
        Equipamento equipamento = equipamentoService.buscar(pedido.equipamentoId());
        Colaborador colaborador = colaboradorService.buscar(pedido.colaboradorId());

        if(!equipamento.isDisponivel()){
            throw new ConflitoException("Equipamento indisponível");
        }

        boolean possuiAtraso = repository.existsByColaboradorIdAndDataDevolucaoIsNullAndDataPrevistaDevolucaoBefore(colaborador.getId(), LocalDate.now());

        if(possuiAtraso){
            throw new ConflitoException("Colaborador possui empréstimo atrasado");
        }

        equipamento.emprestar();

        Emprestimo emprestimo = new Emprestimo(equipamento, colaborador, LocalDate.now());

        return repository.save(emprestimo);
    }

    @Transactional
    public Emprestimo devolver(Long id){
        Emprestimo emprestimo = buscar(id);
        if(emprestimo.getDataDevolucao() != null){
            throw new ConflitoException("Empréstimo já devolvido");
        }

        emprestimo.devolver(LocalDate.now());
        emprestimo.getEquipamento().devolver();

        return repository.save(emprestimo);
    }

    public List<Emprestimo> listar (Boolean atrasados){
        if(Boolean.TRUE.equals(atrasados)){
            return repository.findByDataDevolucaoIsNullAndDataPrevistaDevolucaoBefore(LocalDate.now());
        }
        return repository.findAll();
    }

}
