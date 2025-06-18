package org.example.Classes;

public class Tenis {
    private int id;
    private String marca;
    private String cor;
    private int tamanho;

    // Construtor
    public Tenis(int id, String marca, String cor, int tamanho) {
        this.id = id;
        this.marca = marca;
        this.cor = cor;
        this.tamanho = tamanho;
    }

    // Getters
    public int getId() {
        return id;
    }

    public String getMarca() {
        return marca;
    }

    public String getCor() {
        return cor;
    }

    public int getTamanho() {
        return tamanho;
    }

    // Setters
    public void setId(int id) {
        this.id = id;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public void setCor(String cor) {
        this.cor = cor;
    }

    public void setTamanho(int tamanho) {
        this.tamanho = tamanho;
    }

    // Métodos
    public void exibirDetalhes() {
        System.out.println("Marca: " + marca + ", Cor: " + cor + ", Tamanho: " + tamanho);
    }

    public void mudarTamanho(int novoTamanho) {
        this.tamanho = novoTamanho;
        System.out.println("O tamanho do tênis foi alterado para: " + novoTamanho);
    }

    public boolean compararTamanho(Tenis outro) {
        return this.tamanho == outro.tamanho;
    }

    // Sobrescreve o toString para mostrar detalhes no ListView
    @Override
    public String toString() {
        return "Marca: " + marca + ", Cor: " + cor + ", Tamanho: " + tamanho;
    }
}
