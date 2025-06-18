package org.example;

public class Peixes {
    private String nome;
    private String tipo;
    private int quantidade;

    public Peixes(String nome, String tipo, int quantidade) {
        this.nome = nome;
        this.tipo = tipo;
        this.quantidade = quantidade;
    }

    public String getNomePeixe() {
        return nome;
    }

    public String getTipoPeixe() {
        return tipo;
    }

    public int getQuantidadePeixe() {
        return quantidade;
    }

    public void nomear() {
        System.out.println("O nome do peixe é: " + nome);
    }

    public void especificar() {
        System.out.println("O tipo do peixe é: " + tipo);
    }

    public void contar() {
        System.out.println("A quantidade de peixes é: " + quantidade);
    }
}
