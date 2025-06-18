package org.example;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class PeixeController {

    @FXML
    private TextField nomePeixe;

    @FXML
    private TextField tipoPeixe;

    @FXML
    private TextField quantidadePeixe;

    @FXML
    private TextArea areaTextoPeixes;

    @FXML
    private ImageView imagemPeixe;

    private List<Peixe> peixes = new ArrayList<>();

    private int indiceEdicao = -1;

    @FXML
    private Button btnAdicionar;

    @FXML
    private Button btnEditar;

    @FXML
    private Button btnVoltar;

    @FXML
    private void adicionarPeixe() {
        String nome = nomePeixe.getText().trim();
        String tipo = tipoPeixe.getText().trim();
        String quantidadeStr = quantidadePeixe.getText().trim();

        if (nome.isEmpty() || tipo.isEmpty() || quantidadeStr.isEmpty()) {
            alert("Preencha todos os campos.");
            return;
        }

        int quantidade;
        try {
            quantidade = Integer.parseInt(quantidadeStr);
        } catch (NumberFormatException e) {
            alert("Quantidade deve ser um número inteiro.");
            return;
        }

        if (indiceEdicao >= 0) {
            peixes.set(indiceEdicao, new Peixe(nome, tipo, quantidade));
            indiceEdicao = -1;
            btnAdicionar.setText("Adicionar Peixe");
        } else {
            peixes.add(new Peixe(nome, tipo, quantidade));
        }

        limparCampos();
        visualizarPeixes();
    }

    @FXML
    private void visualizarPeixes() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < peixes.size(); i++) {
            Peixe p = peixes.get(i);
            sb.append(i).append(": ")
                    .append("Nome: ").append(p.getNomePeixe())
                    .append(", Tipo: ").append(p.getTipoPeixe())
                    .append(", Quantidade: ").append(p.getQuantidadePeixe())
                    .append("\n");
        }
        areaTextoPeixes.setText(sb.toString());
    }

    @FXML
    private void editarPeixe() {
        String texto = areaTextoPeixes.getSelectedText();
        if (texto.isEmpty()) {
            alert("Selecione um peixe para editar na área de texto.");
            return;
        }

        int index = -1;
        try {
            index = Integer.parseInt(texto.split(":")[0].trim());
        } catch (Exception e) {
            alert("Seleção inválida. Selecione o número do peixe no começo da linha.");
            return;
        }

        if (index < 0 || index >= peixes.size()) {
            alert("Índice inválido.");
            return;
        }

        Peixe p = peixes.get(index);
        nomePeixe.setText(p.getNomePeixe());
        tipoPeixe.setText(p.getTipoPeixe());
        quantidadePeixe.setText(String.valueOf(p.getQuantidadePeixe()));

        indiceEdicao = index;
        btnAdicionar.setText("Salvar Alterações");
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
        nomePeixe.clear();
        tipoPeixe.clear();
        quantidadePeixe.clear();
        indiceEdicao = -1;
        btnAdicionar.setText("Adicionar Peixe");
    }

    private void alert(String msg) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Atenção");
        alert.setContentText(msg);
        alert.showAndWait();
    }
}
