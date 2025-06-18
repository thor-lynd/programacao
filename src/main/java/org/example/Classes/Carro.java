package org.example.Classes;

public class Carro {
    private int id;
    private String modelo;
    private String marca;
    private int ano;

    // Construtor
    public Carro(int id, String modelo, String marca, int ano) {
        this.id = id;
        this.modelo = modelo;
        this.marca = marca;
        this.ano = ano;
    }

    // Getters
    public int getId() {
        return id;
    }

    public String getModelo() {
        return modelo;
    }

    public String getMarca() {
        return marca;
    }

    public int getAno() {
        return ano;
    }

    // Setters
    public void setId(int id) {
        this.id = id;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public void setAno(int ano) {
        this.ano = ano;
    }

    // Métodos de ação
    public void ligar() {
        System.out.println("O carro " + modelo + " da marca " + marca + " está ligado!");
    }

    public void desligar() {
        System.out.println("O carro " + modelo + " foi desligado.");
    }

    public void acelerar() {
        System.out.println("O carro " + modelo + " está acelerando!");
    }

    @Override
    public String toString() {
        return marca + " " + modelo + " (" + ano + ")";
    }
}
