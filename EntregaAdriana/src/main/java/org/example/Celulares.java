package org.example;

public class Celulares {
    protected String celMarca;
    protected String celModelo;
    protected double celPreco;

    public Celulares(String celMarca, String celModelo, double celPreco) {
        this.celMarca = celMarca;
        this.celModelo = celModelo;
        this.celPreco = celPreco;
    }

    public String ligar() {
        return "O celular " + celMarca + " " + celModelo + " está ligando.";
    }

    public String desligar() {
        return "O celular " + celMarca + " " + celModelo + " está desligando.";
    }

    public String mostrarInfo() {
        return "Informações do Celular:\n" +
                "Marca: " + celMarca + "\n" +
                "Modelo: " + celModelo + "\n" +
                "Preço: R$" + celPreco;
    }
}
