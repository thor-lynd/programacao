package org.example.Classes;

public class Album {
    private int id;
    private String nome;
    private int musicas;
    private String artista;

    // Construtor
    public Album(int id, String nome, int musicas, String artista) {
        this.id = id;
        this.nome = nome;
        this.musicas = musicas;
        this.artista = artista;
    }

    // Getters
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

    // Setters
    public void setId(int id) {
        this.id = id;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setMusicas(int musicas) {
        this.musicas = musicas;
    }

    public void setArtista(String artista) {
        this.artista = artista;
    }

    // Métodos de ação
    public void escutar() {
        System.out.println("Você está escutando o álbum " + nome + " do/a artista " + artista + ".");
    }

    public void skippar() {
        System.out.println("Você pulou o álbum " + nome + ". Quantidade de músicas do álbum atual: " + musicas + ".");
    }

    public void acabar() {
        System.out.println("O álbum " + nome + " acabou.");
    }

    // Exibição no ListView
    @Override
    public String toString() {
        return nome + " - " + artista + " (" + musicas + " músicas)";
    }
}
