package org.example;
public class Carros {
    private String modelo;
    private String marca;
    private int ano;

    public Carros(String modelo, String marca, int ano) {
        this.modelo = modelo;
        this.marca = marca;
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
