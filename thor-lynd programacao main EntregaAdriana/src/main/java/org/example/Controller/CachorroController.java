package org.example;

import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CachorroController {

    @FXML
    private TextField nomeCachorro;

    @FXML
    private TextField racaCachorro;

    @FXML
    private TextField idadeCachorro;

    @FXML
    private TextArea areaTextoCachorros;

    private List<Cachorro> cachorros = new ArrayList<>();

    private int indiceEdicao = -1;

    @FXML
    private void adicionarCachorro() {
        String nome = nomeCachorro.getText().trim();
        String raca = racaCachorro.getText().trim();
        String idadeStr = idadeCachorro.getText().trim();

        if (nome.isEmpty() || raca.isEmpty() || idadeStr.isEmpty()) {
            alert("Preencha todos os campos.");
            return;
        }

        int idade;
        try {
            idade = Integer.parseInt(idadeStr);
        } catch (NumberFormatException e) {
            alert("Idade inválida.");
            return;
        }

        if (indiceEdicao >= 0) {
            Cachorro c = cachorros.get(indiceEdicao);
            c.setNome(nome);
            c.setRaca(raca);
            c.setIdade(idade);
            indiceEdicao = -1;
        } else {
            Cachorro cachorro = new Cachorro(nome, raca, idade);
            cachorros.add(cachorro);
        }

        limparCampos();
        visualizarCachorros();
    }

    @FXML
    private void visualizarCachorros() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < cachorros.size(); i++) {
            Cachorro c = cachorros.get(i);
            sb.append(i).append(": Nome: ").append(c.getNome())
                    .append(", Raça: ").append(c.getRaca())
                    .append(", Idade: ").append(c.getIdade())
                    .append("\n");
        }
        areaTextoCachorros.setText(sb.toString());
    }

    @FXML
    private void editarCachorro() {
        String texto = areaTextoCachorros.getSelectedText();
        if (texto.isEmpty()) {
            alert("Selecione um cachorro para editar na área de texto.");
            return;
        }

        int index = -1;
        try {
            String[] parts = texto.split(":");
            index = Integer.parseInt(parts[0].trim());
        } catch (Exception e) {
            alert("Seleção inválida. Selecione o número do cachorro no começo da linha.");
            return;
        }

        if (index < 0 || index >= cachorros.size()) {
            alert("Índice inválido.");
            return;
        }

        Cachorro c = cachorros.get(index);
        nomeCachorro.setText(c.getNome());
        racaCachorro.setText(c.getRaca());
        idadeCachorro.setText(String.valueOf(c.getIdade()));

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
        nomeCachorro.clear();
        racaCachorro.clear();
        idadeCachorro.clear();
        indiceEdicao = -1;
    }

    private void alert(String msg) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Atenção");
        alert.setContentText(msg);
        alert.showAndWait();
    }
}
