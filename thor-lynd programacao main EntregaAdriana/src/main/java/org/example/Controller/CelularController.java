package org.example;

import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.util.ArrayList;
import java.util.List;

public class CelularController {

    @FXML private TextField marcaCelular;
    @FXML private TextField modeloCelular;
    @FXML private TextField precoCelular;
    @FXML private TextArea areaTextoCelular;

    private final List<Celular> listaCelulares = new ArrayList<>();

    @FXML
    public void adicionarCelular() {
        try {
            String marca = marcaCelular.getText();
            String modelo = modeloCelular.getText();
            double preco = Double.parseDouble(precoCelular.getText());

            Celular celular = new Celular(marca, modelo, preco);
            listaCelulares.add(celular);

            limparCampos();
            areaTextoCelular.setText("Celular adicionado com sucesso!");
        } catch (NumberFormatException e) {
            areaTextoCelular.setText("Erro: preço deve ser um número válido.");
        }
    }

    @FXML
    public void visualizarCelulares() {
        if (listaCelulares.isEmpty()) {
            areaTextoCelular.setText("Nenhum celular cadastrado.");
            return;
        }

        StringBuilder sb = new StringBuilder();
        for (Celular c : listaCelulares) {
            sb.append("Marca: ").append(c.celMarca).append("\n");
            sb.append("Modelo: ").append(c.celModelo).append("\n");
            sb.append("Preço: R$").append(String.format("%.2f", c.celPreco)).append("\n");
            sb.append("------------------------\n");
        }
        areaTextoCelular.setText(sb.toString());
    }

    private void limparCampos() {
        marcaCelular.clear();
        modeloCelular.clear();
        precoCelular.clear();
    }
}
