package org.example;

public class Flores {
    private String nome;
    private String cor;
    private int altura;

    public Flores(String nome, String cor, int altura) {
        this.nome = nome;
        this.cor = cor;
        this.altura = altura;
    }

    public String getNomeFlor() {
        return nome;
    }

    public String getCorFlor() {
        return cor;
    }

    public int getAlturaFlor() {
        return altura;
    }

    public void florescer() {
        System.out.println("A flor " + nome + " de cor " + cor + " está florescendo!");
    }

    public void crescer(int crescimento) {
        altura += crescimento;
        System.out.println("A flor " + nome + " cresceu. Nova altura: " + altura + " cm.");
    }

    public void murchar() {
        System.out.println("A flor " + nome + " de cor " + cor + " murchou.");
    }
}
