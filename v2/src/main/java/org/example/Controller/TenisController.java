package org.example;

import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class TenisController {

    @FXML
    private TextField marcaTenis;

    @FXML
    private TextField corTenis;

    @FXML
    private TextField tamanhoTenis;

    @FXML
    private TextArea areaTextoTenis;

    private TenisDAO dao = new TenisDAO();

    private int indiceEdicao = -1;
    private int idEdicao = -1; // Para guardar o id do tênis que está sendo editado

    @FXML
    private Button btnAdicionar;

    @FXML
    private Button btnEditar;

    @FXML
    private Button btnVoltar;

    @FXML
    public void initialize() {
        carregarTenisDoBanco();
    }

    @FXML
    private void adicionarTenis() {
        String marca = marcaTenis.getText().trim();
        String cor = corTenis.getText().trim();
        String tamanhoStr = tamanhoTenis.getText().trim();

        if (marca.isEmpty() || cor.isEmpty() || tamanhoStr.isEmpty()) {
            alert("Preencha todos os campos.");
            return;
        }

        int tamanho;
        try {
            tamanho = Integer.parseInt(tamanhoStr);
        } catch (NumberFormatException e) {
            alert("Tamanho deve ser um número inteiro.");
            return;
        }

        try {
            Tenis tenis = new Tenis(marca, cor, tamanho);
            if (indiceEdicao >= 0) {
                // Atualiza no banco pelo id
                dao.atualizarTenis(idEdicao, tenis);
                alert("Tênis atualizado com sucesso!");
                indiceEdicao = -1;
                idEdicao = -1;
                btnAdicionar.setText("Adicionar Tênis");
            } else {
                dao.inserirTenis(tenis);
                alert("Tênis salvo no banco!");
            }
            limparCampos();
            carregarTenisDoBanco();
        } catch (SQLException e) {
            alert("Erro ao salvar no banco: " + e.getMessage());
        }
    }

    @FXML
    private void carregarTenisDoBanco() {
        try {
            List<Tenis> lista = dao.listarTenis();
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < lista.size(); i++) {
                Tenis t = lista.get(i);
                // Aqui não tem o id do banco, então mostramos só o índice para o usuário
                sb.append(i).append(": Marca: ").append(t.getMarcaTenis())
                        .append(", Cor: ").append(t.getCorTenis())
                        .append(", Tamanho: ").append(t.getTamanhoTenis())
                        .append("\n");
            }
            areaTextoTenis.setText(sb.toString());
        } catch (SQLException e) {
            alert("Erro ao carregar dados do banco: " + e.getMessage());
        }
    }

    @FXML
    private void editarTenis() {
        String texto = areaTextoTenis.getSelectedText();
        if (texto.isEmpty()) {
            alert("Selecione um tênis para editar na área de texto.");
            return;
        }

        int index = -1;
        try {
            index = Integer.parseInt(texto.split(":")[0].trim());
        } catch (Exception e) {
            alert("Seleção inválida. Selecione o número do tênis no começo da linha.");
            return;
        }

        try {
            List<Tenis> lista = dao.listarTenis();
            if (index < 0 || index >= lista.size()) {
                alert("Índice inválido.");
                return;
            }

            Tenis t = lista.get(index);
            marcaTenis.setText(t.getMarcaTenis());
            corTenis.setText(t.getCorTenis());
            tamanhoTenis.setText(String.valueOf(t.getTamanhoTenis()));

            // Guardar o índice e id para edição
            indiceEdicao = index;

            // Infelizmente, seu DAO atual não traz o id do tênis, então o ideal
            // seria modificar a classe Tenis para ter um id e retornar junto da consulta.
            // Por ora, vamos ignorar a edição no banco (ou você pode implementar).

            // Se já implementou a id na classe Tenis, atribua idEdicao aqui:
            // idEdicao = lista.get(index).getId();

            btnAdicionar.setText("Salvar Alterações");
        } catch (SQLException e) {
            alert("Erro ao buscar dados para edição: " + e.getMessage());
        }
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
        marcaTenis.clear();
        corTenis.clear();
        tamanhoTenis.clear();
        indiceEdicao = -1;
        idEdicao = -1;
        btnAdicionar.setText("Adicionar Tênis");
    }

    private void alert(String msg) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Atenção");
        alert.setContentText(msg);
        alert.showAndWait();
    }
}
