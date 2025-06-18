package org.example;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class PessoaController {

    @FXML
    private TextField nomePessoa;

    @FXML
    private TextField idadePessoa;

    @FXML
    private TextField ocupacaoPessoa;

    @FXML
    private TextArea areaTextoPessoas;

    @FXML
    private ImageView imagemPessoa;

    private List<Pessoa> pessoas = new ArrayList<>();

    private int indiceEdicao = -1;

    @FXML
    private Button btnAdicionar;

    @FXML
    private Button btnEditar;

    @FXML
    private Button btnVoltar;

    @FXML
    private void adicionarPessoa() {
        String nome = nomePessoa.getText().trim();
        String idadeStr = idadePessoa.getText().trim();
        String ocupacao = ocupacaoPessoa.getText().trim();

        if (nome.isEmpty() || idadeStr.isEmpty() || ocupacao.isEmpty()) {
            alert("Preencha todos os campos.");
            return;
        }

        int idade;
        try {
            idade = Integer.parseInt(idadeStr);
        } catch (NumberFormatException e) {
            alert("Idade deve ser um número inteiro.");
            return;
        }

        if (indiceEdicao >= 0) {
            pessoas.set(indiceEdicao, new Pessoa(nome, idade, ocupacao));
            indiceEdicao = -1;
            btnAdicionar.setText("Adicionar Pessoa");
        } else {
            pessoas.add(new Pessoa(nome, idade, ocupacao));
        }

        limparCampos();
        visualizarPessoas();
    }

    @FXML
    private void visualizarPessoas() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < pessoas.size(); i++) {
            Pessoa p = pessoas.get(i);
            sb.append(i).append(": ")
                    .append("Nome: ").append(p.getNomePessoa())
                    .append(", Idade: ").append(p.getIdadePessoa())
                    .append(", Ocupação: ").append(p.getOcupacaoPessoa())
                    .append("\n");
        }
        areaTextoPessoas.setText(sb.toString());
    }

    @FXML
    private void editarPessoa() {
        String texto = areaTextoPessoas.getSelectedText();
        if (texto.isEmpty()) {
            alert("Selecione uma pessoa para editar na área de texto.");
            return;
        }

        int index = -1;
        try {
            index = Integer.parseInt(texto.split(":")[0].trim());
        } catch (Exception e) {
            alert("Seleção inválida. Selecione o número da pessoa no começo da linha.");
            return;
        }

        if (index < 0 || index >= pessoas.size()) {
            alert("Índice inválido.");
            return;
        }

        Pessoa p = pessoas.get(index);
        nomePessoa.setText(p.getNomePessoa());
        idadePessoa.setText(String.valueOf(p.getIdadePessoa()));
        ocupacaoPessoa.setText(p.getOcupacaoPessoa());

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
        nomePessoa.clear();
        idadePessoa.clear();
        ocupacaoPessoa.clear();
        indiceEdicao = -1;
        btnAdicionar.setText("Adicionar Pessoa");
    }

    private void alert(String msg) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Atenção");
        alert.setContentText(msg);
        alert.showAndWait();
    }
}
