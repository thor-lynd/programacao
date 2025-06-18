package org.example;

import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CarroController {

    @FXML
    private TextField modeloCarro;

    @FXML
    private TextField marcaCarro;

    @FXML
    private TextField anoCarro;

    @FXML
    private TextArea areaTextoCarros;

    private List<Carro> carros = new ArrayList<>();

    private int indiceEdicao = -1;

    @FXML
    private void adicionarCarro() {
        String modelo = modeloCarro.getText().trim();
        String marca = marcaCarro.getText().trim();
        String anoStr = anoCarro.getText().trim();

        if (modelo.isEmpty() || marca.isEmpty() || anoStr.isEmpty()) {
            alert("Preencha todos os campos.");
            return;
        }

        int ano;
        try {
            ano = Integer.parseInt(anoStr);
        } catch (NumberFormatException e) {
            alert("Ano inválido.");
            return;
        }

        if (indiceEdicao >= 0) {
            Carro c = carros.get(indiceEdicao);
            c.setModelo(modelo);
            c.setMarca(marca);
            c.setAno(ano);
            indiceEdicao = -1;
        } else {
            Carro carro = new Carro(modelo, marca, ano);
            carros.add(carro);
        }

        limparCampos();
        visualizarCarros();
    }

    @FXML
    private void visualizarCarros() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < carros.size(); i++) {
            Carro c = carros.get(i);
            sb.append(i).append(": Modelo: ").append(c.getModelo())
                    .append(", Marca: ").append(c.getMarca())
                    .append(", Ano: ").append(c.getAno())
                    .append("\n");
        }
        areaTextoCarros.setText(sb.toString());
    }

    @FXML
    private void editarCarro() {
        String texto = areaTextoCarros.getSelectedText();
        if (texto.isEmpty()) {
            alert("Selecione um carro para editar na área de texto.");
            return;
        }

        int index = -1;
        try {
            String[] parts = texto.split(":");
            index = Integer.parseInt(parts[0].trim());
        } catch (Exception e) {
            alert("Seleção inválida. Selecione o número do carro no começo da linha.");
            return;
        }

        if (index < 0 || index >= carros.size()) {
            alert("Índice inválido.");
            return;
        }

        Carro c = carros.get(index);
        modeloCarro.setText(c.getModelo());
        marcaCarro.setText(c.getMarca());
        anoCarro.setText(String.valueOf(c.getAno()));

        indiceEdicao = index;
    }

    @FXML
    private void voltarMenu() {
        try {
            App.setRoot("menu");
        } catch (IOException e) {
            e.printStackTrace();
            alert("Erro ao voltar para o menu.");
        }
    }

    private void limparCampos() {
        modeloCarro.clear();
        marcaCarro.clear();
        anoCarro.clear();
        indiceEdicao = -1;
    }

    private void alert(String msg) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Atenção");
        alert.setContentText(msg);
        alert.showAndWait();
    }
}
