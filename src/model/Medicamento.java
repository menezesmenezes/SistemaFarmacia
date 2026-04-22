package model;

import java.sql.Date;

public class Medicamento {

    private String nome;
    private int quantidade;
    private Date validade;

    public Medicamento(String nome, int quantidade, Date validade) {
        this.nome = nome;
        this.quantidade = quantidade;
        this.validade = validade;
    }

    public String getNome() {
        return nome;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public Date getValidade() {
        return validade;
    }
}