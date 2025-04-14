package org.example;

import java.io.IOException;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class PrimaryController {
//CELULAR
    @FXML
    private TextField marcaCelular;
    @FXML
    private TextField modeloCelular;
    @FXML
    private TextField precoCelular;
    @FXML
    private Button salvarCelular;

    public void adicionarCelular() throws IOException {
        String marca = marcaCelular.getText();
        String modelo = modeloCelular.getText();
        double preco = Double.parseDouble(precoCelular.getText()); // converte a string recebida pra double

        Celulares celular = new Celulares(marca,modelo,preco);

        FXMLLoader loader = new FXMLLoader(getClass().getResource("secondary.fxml"));
        Parent root = loader.load();

        SecondaryController controller = loader.getController();
        controller.setCelular(celular);

        Stage stage = (Stage) marcaCelular.getScene().getWindow();
        stage.setScene(new Scene(root));
    }
//FIMCELULAR

    // ALBUM
    @FXML
    private TextField nomeAlbum;
    @FXML
    private TextField quantidadeAlbum;
    @FXML
    private TextField artistaAlbum;
    @FXML
    private Button salvarAlbum;

    public void adicionarAlbum() throws IOException {
        String nome = nomeAlbum.getText();
        int musicas = Integer.parseInt(quantidadeAlbum.getText());
        String artista = artistaAlbum.getText();

        Albums album = new Albums(nome, musicas, artista);

        FXMLLoader loader = new FXMLLoader(getClass().getResource("secondary.fxml"));
        Parent root = loader.load();

        SecondaryController controller = loader.getController();
        controller.setAlbum(album);

        Stage stage = (Stage) nomeAlbum.getScene().getWindow();
        stage.setScene(new Scene(root));
    }
// FIMALBUM



}