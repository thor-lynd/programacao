package org.example.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.Node;
import java.io.IOException;

public class MenuController {

    private void abrirTela(ActionEvent event, String nomeFXML) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/org/example/" + nomeFXML + ".fxml"));
            Scene scene = new Scene(fxmlLoader.load());
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void openAlbum(ActionEvent event) {
        abrirTela(event, "Album");
    }

    @FXML
    public void openCachorro(ActionEvent event) {
        abrirTela(event, "Cachorro");
    }

    @FXML
    public void openCarros(ActionEvent event) {
        abrirTela(event, "Carro");
    }

    @FXML
    public void openCelular(ActionEvent event) {
        abrirTela(event, "Celular");
    }

    @FXML
    public void openFlor(ActionEvent event) {
        abrirTela(event, "Flor");
    }

    @FXML
    public void openManga(ActionEvent event) {
        abrirTela(event, "Manga");
    }

    @FXML
    public void openPeixe(ActionEvent event) {
        abrirTela(event, "Peixe");
    }

    @FXML
    public void openPessoa(ActionEvent event) {
        abrirTela(event, "Pessoa");
    }

    @FXML
    public void openTenis(ActionEvent event) {
        abrirTela(event, "Tenis");
    }

    @FXML
    public void openBicicleta(ActionEvent event) {
        abrirTela(event, "Bicicleta");
    }

    @FXML
    public void openLivro(ActionEvent event) {
        abrirTela(event, "Livro");
    }
}
