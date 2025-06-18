package org.example.Classes;

public class Cachorro {
    private int id;
    private String nome;
    private String raca;
    private int idade;

    // Construtor
    public Cachorro(int id, String nome, String raca, int idade) {
        this.id = id;
        this.nome = nome;
        this.raca = raca;
        this.idade = idade;
    }

    // Getters
    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getRaca() {
        return raca;
    }

    public int getIdade() {
        return idade;
    }

    // Setters
    public void setId(int id) {
        this.id = id;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setRaca(String raca) {
        this.raca = raca;
    }

    public void setIdade(int idade) {
        this.idade = idade;
    }

    // Métodos de ação
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
