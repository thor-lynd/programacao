package org.example;
public class Albums {
    private String nome;
    private int musicas;
    private String artista;

    public Albums(String nome, int musicas, String artista) {
        this.nome = nome;
        this.musicas = musicas;
        this.artista = artista;
    }

    public void escutar() {
        System.out.println("Você está escutando o álbum " + nome + " do/a artista " + artista + ".");
    }

    public void skippar() {
        System.out.println("Você pulou o álbum " + nome + ". Quantidade de músicas do álbum atual: " + musicas + ".");
    }

    public void acabar() {
        System.out.println("O álbum " + nome + " acabou.");
    }
}