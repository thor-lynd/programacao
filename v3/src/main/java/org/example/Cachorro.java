package org.example;

public class Cachorro {
    private int id;
    private String nome;
    private String raca;
    private int idade;

    // Construtor sem ID (para novos registros)
    public Cachorro(String nome, String raca, int idade) {
        this.nome = nome;
        this.raca = raca;
        this.idade = idade;
    }

    // Construtor com ID (para registros vindos do banco)
    public Cachorro(int id, String nome, String raca, int idade) {
        this.id = id;
        this.nome = nome;
        this.raca = raca;
        this.idade = idade;
    }

    // Getters e setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getRaca() {
        return raca;
    }

    public void setRaca(String raca) {
        this.raca = raca;
    }

    public int getIdade() {
        return idade;
    }

    public void setIdade(int idade) {
        this.idade = idade;
    }

    // Métodos extras nao vou usar nao
    public void latir() {
        System.out.println(nome + " está latindo!");
    }

    public void falarIdade() {
        System.out.println(nome + " tem " + idade + " anos.");
    }

    public void falarRaca() {
        System.out.println(nome + " é da raça: " + raca);
    }
}
