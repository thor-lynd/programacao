package org.example;

public class Mangas {
    private String nome;
    private int capitulo;
    private int nota;

    public Mangas(String nome, int capitulo, int nota) {
        this.nome = nome;
        this.capitulo = capitulo;
        this.nota = nota;
    }

    public String getNomeManga() {
        return nome;
    }

    public int getCapituloManga() {
        return capitulo;
    }

    public int getNotaManga() {
        return nota;
    }

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
