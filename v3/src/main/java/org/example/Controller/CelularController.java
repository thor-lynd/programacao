package org.example;

import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.sql.SQLException;
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

    private CelularDAO dao = new CelularDAO();

    // Armazena o último celular inserido para edição posterior
    private Celular ultimoCelularInserido = null;

    @FXML
    private void adicionarCelular() {
        try {
            String marca = marcaCelular.getText().trim();
            String modelo = modeloCelular.getText().trim();
            double preco = Double.parseDouble(precoCelular.getText().trim());

            if (marca.isEmpty() || modelo.isEmpty()) {
                areaTextoCelulares.setText("Marca e Modelo não podem ser vazios.");
                return;
            }

            Celular c = new Celular(marca, modelo, preco);
            dao.inserir(c);
            ultimoCelularInserido = c;

            areaTextoCelulares.setText("Celular adicionado com sucesso:\n" + c.mostrarInfo());
            limparCampos();

        } catch (NumberFormatException e) {
            areaTextoCelulares.setText("Erro: Preço inválido.");
        } catch (SQLException e) {
            areaTextoCelulares.setText("Erro ao inserir no banco: " + e.getMessage());
        }
    }

    @FXML
    private void visualizarCelulares() {
        try {
            List<Celular> lista = dao.listarTodos();
            if (lista.isEmpty()) {
                areaTextoCelulares.setText("Nenhum celular cadastrado.");
                return;
            }

            StringBuilder sb = new StringBuilder("Celulares cadastrados:\n\n");
            for (Celular c : lista) {
                sb.append(c.mostrarInfo()).append("\n\n");
            }
            areaTextoCelulares.setText(sb.toString());

        } catch (SQLException e) {
            areaTextoCelulares.setText("Erro ao buscar no banco: " + e.getMessage());
        }
    }

    @FXML
    private void editarUltimoCelular() {
        if (ultimoCelularInserido == null) {
            areaTextoCelulares.setText("Nenhum celular para editar. Adicione um primeiro.");
            return;
        }

        try {
            String novaMarca = marcaCelular.getText().trim();
            String novoModelo = modeloCelular.getText().trim();
            double novoPreco = Double.parseDouble(precoCelular.getText().trim());

            if (novaMarca.isEmpty() || novoModelo.isEmpty()) {
                areaTextoCelulares.setText("Marca e Modelo não podem ser vazios.");
                return;
            }

            Celular celularAtualizado = new Celular(novaMarca, novoModelo, novoPreco);

            // Atualiza no banco usando modelo antigo como chave
            dao.atualizar(celularAtualizado, ultimoCelularInserido.getCelModelo());

            areaTextoCelulares.setText("Último celular atualizado com sucesso:\n" + celularAtualizado.mostrarInfo());

            ultimoCelularInserido = celularAtualizado;
            limparCampos();

        } catch (NumberFormatException e) {
            areaTextoCelulares.setText("Erro: Preço inválido.");
        } catch (SQLException e) {
            areaTextoCelulares.setText("Erro ao atualizar no banco: " + e.getMessage());
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
        marcaCelular.clear();
        modeloCelular.clear();
        precoCelular.clear();
    }
}
