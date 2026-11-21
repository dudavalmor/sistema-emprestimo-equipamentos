package com.projeto.emprestimo.model;

public enum TipoEquipamento {
    NOTEBOOK("Notebook", 14),
    MONITOR("Monitor", 14),
    MOUSE("Mouse", 14),
    TECLADO("Teclado", 14),
    HEADSET("Headset", 14),
    OUTRO("Outro", 14);

    private final String descricao;
    private final int prazoEmDias;

    TipoEquipamento (String descricao, int prazoEmDias){
        this.descricao = descricao;
        this.prazoEmDias = prazoEmDias;
    }

    public String getDescricao(){
        return descricao;
    }

    public int getPrazoEmDias(){
        return prazoEmDias;
    }
}
