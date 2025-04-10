package org.example;
public class Celulares {
    private String marca;
    private String modelo;
    private double preco;

    public Celulares(String marca, String modelo, double preco) {
        this.marca = marca;
        this.modelo = modelo;
        this.preco = preco;
    }

    public void ligar() {
        System.out.println("O celular " + marca + " " + modelo + " está ligando.");
    }

    public void desligar() {
        System.out.println("O celular " + marca + " " + modelo + " está desligando.");
    }

    public void mostrarInfo() {
        System.out.println("Informações do Celular:");
        System.out.println("Marca: " + marca);
        System.out.println("Modelo: " + modelo);
        System.out.println("Preço: R$" + preco);
    }
}
