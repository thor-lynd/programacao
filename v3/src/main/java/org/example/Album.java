package org.example;

public class Album {
    private int id;
    private String nome;
    private int musicas;
    private String artista;

    public Album(String nome, int musicas, String artista) {
        this.nome = nome;
        this.musicas = musicas;
        this.artista = artista;
    }

    public Album(int id, String nome, int musicas, String artista) {
        this.id = id;
        this.nome = nome;
        this.musicas = musicas;
        this.artista = artista;
    }

    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public int getMusicas() {
        return musicas;
    }

    public String getArtista() {
        return artista;
    }
}
