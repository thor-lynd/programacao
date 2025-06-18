package org.example;

public class Celular {
    protected String celMarca;
    protected String celModelo;
    protected double celPreco;

    public Celular(String celMarca, String celModelo, double celPreco) {
        this.celMarca = celMarca;
        this.celModelo = celModelo;
        this.celPreco = celPreco;
    }

    public String getCelMarca() {
        return celMarca;
    }

    public void setCelMarca(String celMarca) {
        this.celMarca = celMarca;
    }

    public String getCelModelo() {
        return celModelo;
    }

    public void setCelModelo(String celModelo) {
        this.celModelo = celModelo;
    }

    public double getCelPreco() {
        return celPreco;
    }

    public void setCelPreco(double celPreco) {
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
