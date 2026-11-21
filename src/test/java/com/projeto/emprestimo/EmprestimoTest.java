package com.projeto.emprestimo;

import com.projeto.emprestimo.model.Colaborador;
import com.projeto.emprestimo.model.Emprestimo;
import com.projeto.emprestimo.model.Equipamento;
import com.projeto.emprestimo.model.TipoEquipamento;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

public class EmprestimoTest {

    @Test
    void emprestimoVencidoENaoDevolvidoEstaAtrasado(){
        Emprestimo emprestimo = new Emprestimo();
        emprestimo.setDataPrevistaDevolucao(LocalDate.now().minusDays(1));

        assertTrue(emprestimo.isAtrasado());
    }

    @Test
    void emprestimoDentroDoPrazoNaoEstaAtrasado(){
        Emprestimo emprestimo = new Emprestimo();
        emprestimo.setDataPrevistaDevolucao(LocalDate.now().plusDays(1));

        assertFalse(emprestimo.isAtrasado());
    }

    @Test
    void emprestimoDevolvidoNaoEstaAtrasado(){
        Emprestimo emprestimo = new Emprestimo();
        emprestimo.setDataPrevistaDevolucao(LocalDate.now().minusDays(1));
        emprestimo.devolver(LocalDate.now());

        assertFalse(emprestimo.isAtrasado());
    }
    
    @Test
    void naoPermitirDevolverEmprestimoDuasVezes(){
        Emprestimo emprestimo = new Emprestimo();
        emprestimo.devolver(LocalDate.now());

        assertThrows(IllegalStateException.class, () -> {
            emprestimo.devolver(LocalDate.now());
        });
    }

    @Test
    void criarEmprestimoComDatasCorretas(){
        Equipamento equipamento = new Equipamento();
        equipamento.setTipo(TipoEquipamento.MONITOR);

        Colaborador colaborador = new Colaborador();
        LocalDate data = LocalDate.of(2026, 11, 14);

        Emprestimo emprestimo = new Emprestimo(equipamento, colaborador, data);

        assertEquals(data, emprestimo.getDataEmprestimo());

        assertEquals(LocalDate.of(2026, 11, 28), emprestimo.getDataPrevistaDevolucao());
    }

    @Test
    void emprestimoNoDiaDoVencimentoNaoEstaAtrasado() {
        Emprestimo emprestimo = new Emprestimo();

        LocalDate vencimento = LocalDate.of(2026, 11, 14);

        emprestimo.setDataPrevistaDevolucao(vencimento);

        assertFalse(emprestimo.estaAtrasadoEm(vencimento));
    }

    @Test
    void devolverEmprestimoAbertoNaDataEspecifica(){
        Emprestimo emprestimo = new Emprestimo();

        LocalDate data = LocalDate.of(2026, 11, 20);
        emprestimo.devolver(data);

        assertEquals(data, emprestimo.getDataDevolucao());
    }
}
