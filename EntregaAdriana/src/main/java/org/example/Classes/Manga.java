package org.example.Classes;

public class Manga {
    private int id;
    private String nome;
    private int capitulo;
    private int nota;

    // Construtor
    public Manga(int id, String nome, int capitulo, int nota) {
        this.id = id;
        this.nome = nome;
        this.capitulo = capitulo;
        this.nota = nota;
    }

    // Getters
    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public int getCapitulo() {
        return capitulo;
    }

    public int getNota() {
        return nota;
    }

    // Setters
    public void setId(int id) {
        this.id = id;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setCapitulo(int capitulo) {
        this.capitulo = capitulo;
    }

    public void setNota(int nota) {
        this.nota = nota;
    }

    // Métodos de ação
    public void falarNome() {
        System.out.println("O nome do mangá é: " + nome);
    }

    public void falarCapitulo() {
        System.out.println("O capítulo em que está é: " + capitulo);
    }

    public void falarNota() {
        System.out.println("A nota de " + nome + " é: " + nota);
    }
}
