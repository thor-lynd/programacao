package org.example;

import javafx.fxml.FXML;
import javafx.scene.control.TextArea;

public class SecondaryController {

    @FXML
    private TextArea infoGeral;

    private Celulares celular;

    public void setCelular(Celulares celular) {
        this.celular = celular;
        mostrarInformacoesCel();
    }

    private void mostrarInformacoesCel() {
        celular.mostrarInfo();
        String info = "Informações do Celular:\n" +
                "Marca: " + celular.celMarca + "\n" +
                "Modelo: " + celular.celModelo + "\n" +
                "Preço: R$" + celular.celPreco + "\n" +
                "Ação: " + "Ligando...\n" + celular.ligar() + "\n" +
                "Desligando...\n" + celular.desligar();
        infoGeral.setText(info);  // Atualiza a TextArea
    }

    private Albums album;

    public void setAlbum(Albums album) {
        this.album = album;
        mostrarInformacoesAlbum();
    }
    private void mostrarInformacoesAlbum() {
        album.escutar();
        String info = "Informações do Álbum:\n" +
                "Nome: " + album.getNome() + "\n" +
                "Músicas: " + album.getMusicas() + "\n" +
                "Artista: " + album.getArtista() + "\n" +
                "Ações: \n" +
                "- Escutando...\n" +
                "- Pulando...\n" +
                "- Acabando...";
        infoGeral.setText(info);
    }
}