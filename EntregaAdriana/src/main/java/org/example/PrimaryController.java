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



}