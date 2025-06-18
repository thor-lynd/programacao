package org.example.Classes;

public class Pessoa {
    private int id;
    private String nome;
    private int idade;
    private String ocupacao;

    // Construtor
    public Pessoa(int id, String nome, int idade, String ocupacao) {
        this.id = id;
        this.nome = nome;
        this.idade = idade;
        this.ocupacao = ocupacao;
    }

    // Getters
    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public int getIdade() {
        return idade;
    }

    public String getOcupacao() {
        return ocupacao;
    }

    // Setters
    public void setId(int id) {
        this.id = id;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setIdade(int idade) {
        this.idade = idade;
    }

    public void setOcupacao(String ocupacao) {
        this.ocupacao = ocupacao;
    }

    // Métodos de ação
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
