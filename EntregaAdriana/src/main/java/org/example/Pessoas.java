package org.example;

public class Pessoas {
    private String nome;
    private int idade;
    private String ocupacao;

    public Pessoas(String nome, int idade, String ocupacao) {
        this.nome = nome;
        this.idade = idade;
        this.ocupacao = ocupacao;
    }

    public String getNomePessoa() {
        return nome;
    }

    public int getIdadePessoa() {
        return idade;
    }

    public String getOcupacaoPessoa() {
        return ocupacao;
    }

    public void falarNome() {
        System.out.println("Meu nome é " + nome + ".");
    }

    public void falarIdade() {
        System.out.println("Eu tenho " + idade + " anos.");
    }

    public void falarOcupacao() {
        System.out.println("Minha ocupação é " + ocupacao + ".");
    }
}
