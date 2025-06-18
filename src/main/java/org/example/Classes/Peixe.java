package org.example.Classes;

public class Peixe {
    private int id;
    private String nome;
    private String tipo;
    private int quantidade;

    // Construtor
    public Peixe(int id, String nome, String tipo, int quantidade) {
        this.id = id;
        this.nome = nome;
        this.tipo = tipo;
        this.quantidade = quantidade;
    }

    // Getters
    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getTipo() {
        return tipo;
    }

    public int getQuantidade() {
        return quantidade;
    }

    // Setters
    public void setId(int id) {
        this.id = id;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    // Métodos de ação
    public void nomear() {
        System.out.println("O nome do peixe é: " + nome);
    }

    public void especificar() {
        System.out.println("O tipo do peixe é: " + tipo);
    }

    public void contar() {
        System.out.println("A quantidade de peixes é: " + quantidade);
    }


    @Override
    public String toString() {
        return "Nome: " + nome + ", Tipo: " + tipo + ", Quantidade: " + quantidade;
    }
}
