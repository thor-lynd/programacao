package org.example;

import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.sql.SQLException;
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

    private CarroDAO dao = new CarroDAO();

    // Para guardar o modelo selecionado para edição
    private String modeloSelecionado = null;

    @FXML
    private void adicionarCarro() {
        try {
            String modelo = modeloCarro.getText();
            String marca = marcaCarro.getText();
            int ano = Integer.parseInt(anoCarro.getText());

            Carro c = new Carro(modelo, marca, ano);
            dao.inserir(c);

            areaTextoCarros.setText("Carro adicionado com sucesso!");
            limparCampos();
            modeloSelecionado = null;

        } catch (NumberFormatException e) {
            areaTextoCarros.setText("Erro: Ano inválido.");
        } catch (SQLException e) {
            areaTextoCarros.setText("Erro ao acessar o banco: " + e.getMessage());
        }
    }

    @FXML
    private void visualizarCarros() {
        try {
            List<Carro> lista = dao.listarTodos();
            StringBuilder sb = new StringBuilder();
            for (Carro c : lista) {
                sb.append("Modelo: ").append(c.getModelo())
                        .append(", Marca: ").append(c.getMarca())
                        .append(", Ano: ").append(c.getAno())
                        .append("\n");
            }
            areaTextoCarros.setText(sb.toString());
        } catch (SQLException e) {
            areaTextoCarros.setText("Erro ao acessar o banco: " + e.getMessage());
        }
    }

    @FXML
    private void editarCarro() {
        try {
            if (modeloSelecionado == null) {
                areaTextoCarros.setText("Selecione um carro para editar visualizando e copiando o modelo.");
                return;
            }

            String novoModelo = modeloCarro.getText();
            String novaMarca = marcaCarro.getText();
            int novoAno = Integer.parseInt(anoCarro.getText());

            Carro c = new Carro(novoModelo, novaMarca, novoAno);
            dao.atualizar(c, modeloSelecionado);

            areaTextoCarros.setText("Carro atualizado com sucesso!");
            limparCampos();
            modeloSelecionado = null;

        } catch (NumberFormatException e) {
            areaTextoCarros.setText("Erro: Ano inválido.");
        } catch (SQLException e) {
            areaTextoCarros.setText("Erro ao atualizar no banco: " + e.getMessage());
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
        modeloCarro.clear();
        marcaCarro.clear();
        anoCarro.clear();
    }
}
