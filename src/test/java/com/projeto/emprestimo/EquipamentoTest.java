package com.projeto.emprestimo;

import com.projeto.emprestimo.model.Equipamento;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class EquipamentoTest {

    @Test
    void equipamentoDisponivePodeSerEmprestado(){
        Equipamento equipamento = new Equipamento();

        equipamento.setDisponivel(true);
        equipamento.emprestar();

        assertFalse(equipamento.isDisponivel());
    }

    @Test
    void naoPermitirEmprestarEquipamentoJaEmprestado(){
        Equipamento equipamento = new Equipamento();
        equipamento.setDisponivel(true);

        equipamento.emprestar();

        assertThrows(IllegalStateException.class, () -> {
            equipamento.emprestar();
        });
    }

    @Test
    void voltaDisponivelAposDevolucao(){

        Equipamento equipamento = new Equipamento();
        equipamento.setDisponivel(false);
        equipamento.devolver();

        assertTrue(equipamento.isDisponivel());
    }

}
