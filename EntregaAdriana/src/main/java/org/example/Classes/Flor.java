package org.example.Classes;

public class Flor {
    private int id;
    private String nome;
    private String cor;
    private int altura;

    // Construtor
    public Flor(int id, String nome, String cor, int altura) {
        this.id = id;
        this.nome = nome;
        this.cor = cor;
        this.altura = altura;
    }

    // Getters
    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getCor() {
        return cor;
    }

    public int getAltura() {
        return altura;
    }

    // Setters
    public void setId(int id) {
        this.id = id;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setCor(String cor) {
        this.cor = cor;
    }

    public void setAltura(int altura) {
        this.altura = altura;
    }

    // Métodos de ação
    public void florescer() {
        System.out.println("A flor " + nome + " de cor " + cor + " está florescendo!");
    }

    public void crescer(int crescimento) {
        altura += crescimento;
        System.out.println("A flor " + nome + " cresceu. Nova altura: " + altura + " cm.");
    }

    public void murchar() {
        System.out.println("A flor " + nome + " de cor " + cor + " murchou.");
    }
}
