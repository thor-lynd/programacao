package org.example.Classes;

public class Celular {
    private int id;
    private String marca;
    private String modelo;
    private double preco;

    // Construtor
    public Celular(int id, String marca, String modelo, double preco) {
        this.id = id;
        this.marca = marca;
        this.modelo = modelo;
        this.preco = preco;
    }

    // Getters
    public int getId() {
        return id;
    }

    public String getMarca() {
        return marca;
    }

    public String getModelo() {
        return modelo;
    }

    public double getPreco() {
        return preco;
    }

    // Setters
    public void setId(int id) {
        this.id = id;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }

    // Métodos de ação
    public String ligar() {
        return "O celular " + marca + " " + modelo + " está ligando.";
    }

    public String desligar() {
        return "O celular " + marca + " " + modelo + " está desligando.";
    }

    public String mostrarInfo() {
        return "Informações do Celular:\n" +
                "Marca: " + marca + "\n" +
                "Modelo: " + modelo + "\n" +
                "Preço: R$" + preco;
    }
}
