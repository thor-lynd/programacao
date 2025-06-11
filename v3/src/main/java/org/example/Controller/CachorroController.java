package org.example;

import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.sql.SQLException;
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

    private CachorroDAO dao = new CachorroDAO();

    @FXML
    private void adicionarCachorro() {
        try {
            String nome = nomeCachorro.getText();
            String raca = racaCachorro.getText();
            int idade = Integer.parseInt(idadeCachorro.getText());

            Cachorro c = new Cachorro(nome, raca, idade);
            dao.inserir(c);

            areaTextoCachorros.setText("Cachorro adicionado com sucesso!");

            limparCampos();

        } catch (NumberFormatException e) {
            areaTextoCachorros.setText("Erro: Idade inválida.");
        } catch (SQLException e) {
            areaTextoCachorros.setText("Erro ao acessar o banco: " + e.getMessage());
        }
    }

    @FXML
    private void visualizarCachorros() {
        try {
            List<Cachorro> lista = dao.listarTodos();
            StringBuilder sb = new StringBuilder();
            for (Cachorro c : lista) {
                sb.append("ID: ").append(c.getId())
                        .append(", Nome: ").append(c.getNome())
                        .append(", Raça: ").append(c.getRaca())
                        .append(", Idade: ").append(c.getIdade())
                        .append("\n");
            }
            areaTextoCachorros.setText(sb.toString());

        } catch (SQLException e) {
            areaTextoCachorros.setText("Erro ao acessar o banco: " + e.getMessage());
        }
    }

    @FXML
    private void editarCachorro() {
        try {
            int id = Integer.parseInt(areaTextoCachorros.getSelectedText().trim());
            // O ideal seria ter uma forma melhor de selecionar o ID para editar,
            // aqui simplificado: usuário deve selecionar o ID no TextArea

            String nome = nomeCachorro.getText();
            String raca = racaCachorro.getText();
            int idade = Integer.parseInt(idadeCachorro.getText());

            Cachorro c = new Cachorro(id, nome, raca, idade);
            dao.atualizar(c);

            areaTextoCachorros.setText("Cachorro atualizado com sucesso!");
            limparCampos();

        } catch (NumberFormatException e) {
            areaTextoCachorros.setText("Erro: Selecione o ID para editar corretamente e informe campos válidos.");
        } catch (SQLException e) {
            areaTextoCachorros.setText("Erro ao atualizar no banco: " + e.getMessage());
        }
    }

    @FXML
    private void voltarMenu() {
        try {
            App.setRoot("Menu");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void limparCampos() {
        nomeCachorro.clear();
        racaCachorro.clear();
        idadeCachorro.clear();
    }
}
