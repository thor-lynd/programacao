package org.example;

import javafx.fxml.FXML;
import java.io.IOException;

public class MenuController {

    @FXML
    private void abrirAlbum() {
        abrirTela("Album");
    }

    @FXML
    private void abrirCachorro() {
        abrirTela("Cachorro");
    }

    @FXML
    private void abrirCarro() {
        abrirTela("Carro");
    }

    @FXML
    private void abrirCelular() {
        abrirTela("Celular");
    }

    @FXML
    private void abrirFlor() {
        abrirTela("Flor");
    }

    @FXML
    private void abrirManga() {
        abrirTela("Manga");
    }

    @FXML
    private void abrirPeixe() {
        abrirTela("Peixe");
    }

    @FXML
    private void abrirPessoa() {
        abrirTela("Pessoa");
    }

    @FXML
    private void abrirTenis() {
        abrirTela("Tenis");
    }

    private void abrirTela(String nomeFXML) {
        try {
            App.setRoot(nomeFXML);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
