package com.example.api.model;

public class Aposentado {
    private String nome;
    private String cpf;

    // Construtor padrão necessário para o Jackson (conversor JSON do Spring)
    public Aposentado() {}

    public Aposentado(String nome, String cpf) {
        this.nome = nome;
        this.cpf = cpf;
    }

    // Getters e Setters
    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public String getCpf() { return cpf; }
    public void setCpf(String cpf) { this.cpf = cpf; }
}