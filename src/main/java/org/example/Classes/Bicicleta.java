package org.example.Classes;

public class Bicicleta {
    private int id;
    private String modelo;
    private String cor;
    private int marchas;

    public Bicicleta(int id, String modelo, String cor, int marchas) {
        this.id = id;
        this.modelo = modelo;
        this.cor = cor;
        this.marchas = marchas;
    }

    // Getters
    public int getId() {
        return id;
    }

    public String getModelo() {
        return modelo;
    }

    public String getCor() {
        return cor;
    }

    public int getMarchas() {
        return marchas;
    }

    // Setters
    public void setId(int id) {
        this.id = id;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public void setCor(String cor) {
        this.cor = cor;
    }

    public void setMarchas(int marchas) {
        this.marchas = marchas;
    }

    // Métodos
    public void pedalar() {
        System.out.println("A bicicleta modelo " + modelo + " está pedalando.");
    }

    public void frear() {
        System.out.println("A bicicleta modelo " + modelo + " está freando.");
    }

    public void trocarMarcha(int novaMarcha) {
        if(novaMarcha > 0 && novaMarcha <= marchas) {
            System.out.println("Marcha trocada para " + novaMarcha + ".");
        } else {
            System.out.println("Marcha inválida.");
        }
    }

    @Override
    public String toString() {
        return modelo + " - Cor: " + cor + ", Marchas: " + marchas;
    }
}
