package org.example;

import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CelularController {
    @FXML
    private TextField marcaCelular;
    @FXML
    private TextField modeloCelular;
    @FXML
    private TextField precoCelular;
    @FXML
    private TextArea areaTextoCelulares;

    private final List<Celular> celulares = new ArrayList<>();

    @FXML
    private void adicionarCelular() {
        try {
            String marca = marcaCelular.getText();
            String modelo = modeloCelular.getText();
            double preco = Double.parseDouble(precoCelular.getText());

            Celular celular = new Celular(marca, modelo, preco);
            celulares.add(celular);

            marcaCelular.clear();
            modeloCelular.clear();
            precoCelular.clear();

            areaTextoCelulares.setText("Celular adicionado com sucesso!");

        } catch (NumberFormatException e) {
            areaTextoCelulares.setText("Preço inválido.");
        }
    }

    @FXML
    private void visualizarCelulares() {
        StringBuilder sb = new StringBuilder();
        for (Celular c : celulares) {
            sb.append(c.mostrarInfo()).append("\n---\n");
        }
        areaTextoCelulares.setText(sb.toString());
    }

    @FXML
    private void editarUltimoCelular() {
        if (!celulares.isEmpty()) {
            Celular ultimo = celulares.get(celulares.size() - 1);
            ultimo.setCelMarca(marcaCelular.getText());
            ultimo.setCelModelo(modeloCelular.getText());
            try {
                ultimo.setCelPreco(Double.parseDouble(precoCelular.getText()));
                areaTextoCelulares.setText("Último celular editado com sucesso!");
            } catch (NumberFormatException e) {
                areaTextoCelulares.setText("Erro: preço inválido.");
            }
        } else {
            areaTextoCelulares.setText("Nenhum celular para editar.");
        }
    }

    @FXML
    private void voltarMenu() throws IOException {
        App.setRoot("menu");
    }
}
