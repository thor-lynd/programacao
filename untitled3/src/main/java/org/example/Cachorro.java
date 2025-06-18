package org.example;
public class Cachorro {
    private String nome;
    private String raca;
    private int idade;

    public Cachorro(String nome, String raca, int idade) {
        this.nome = nome;
        this.raca = raca;
        this.idade = idade;
    }

    public void latir() {
        System.out.println(nome + " está latindo!");
    }

    public void falarIdade() {
        System.out.println(nome + " Tem " + idade + " anos.");
    }

    public void falarRaca() {
        System.out.println(nome + " é da raça: " + raca);
    }
}