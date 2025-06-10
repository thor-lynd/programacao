package org.example;

public class Tenis {
    private int id;          // novo campo
    private String marca;
    private String cor;
    private int tamanho;

    // Construtor para objetos novos (sem id ainda)
    public Tenis(String marca, String cor, int tamanho) {
        this.marca = marca;
        this.cor = cor;
        this.tamanho = tamanho;
    }

    // Construtor completo com id (para objetos vindos do banco)
    public Tenis(int id, String marca, String cor, int tamanho) {
        this.id = id;
        this.marca = marca;
        this.cor = cor;
        this.tamanho = tamanho;
    }

    // getters e setters do id
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMarcaTenis() {
        return marca;
    }

    public String getCorTenis() {
        return cor;
    }

    public int getTamanhoTenis() {
        return tamanho;
    }

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
}
