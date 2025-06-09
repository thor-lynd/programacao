package org.example;

public class Carro {
    private String modelo;
    private String marca;
    private int ano;

    public Carro(String modelo, String marca, int ano) {
        this.modelo = modelo;
        this.marca = marca;
        this.ano = ano;
    }

    // Getters e setters
    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public int getAno() {
        return ano;
    }

    public void setAno(int ano) {
        this.ano = ano;
    }

    public void ligar() {
        System.out.println("O carro " + modelo + " da marca " + marca + " está ligado!");
    }

    public void desligar() {
        System.out.println("O carro " + modelo + " foi desligado.");
    }

    public void acelerar() {
        System.out.println("O carro " + modelo + " está acelerando!");
    }
}
