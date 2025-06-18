package org.example.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import org.example.Classes.Celular;
import org.example.dao.CelularDAO;

import java.util.Optional;

public class CelularController {

    @FXML
    private ListView<Celular> listViewCelulares;

    private final CelularDAO celularDAO = new CelularDAO();
    private final ObservableList<Celular> celularList = FXCollections.observableArrayList();

    @FXML
    public void initialize() {
        listViewCelulares.setItems(celularList);
        atualizarLista();
    }

    @FXML
    private void handleAdicionar() {
        Celular novo = exibirDialogo(null);
        if (novo != null) {
            celularDAO.adicionar(novo);
            atualizarLista();
        }
    }

    @FXML
    private void handleEditar() {
        Celular selecionado = listViewCelulares.getSelectionModel().getSelectedItem();
        if (selecionado != null) {
            Celular editado = exibirDialogo(selecionado);
            if (editado != null) {
                celularDAO.atualizar(editado);
                atualizarLista();
            }
        } else {
            mostrarAlerta("Selecione um celular para editar.");
        }
    }

    @FXML
    private void handleApagar() {
        Celular selecionado = listViewCelulares.getSelectionModel().getSelectedItem();
        if (selecionado != null) {
            celularDAO.deletar(selecionado.getId());
            atualizarLista();
        } else {
            mostrarAlerta("Selecione um celular para apagar.");
        }
    }

    @FXML
    private void handleAtualizar() {
        atualizarLista();
    }

    @FXML
    private void handleLigar() {
        Celular c = listViewCelulares.getSelectionModel().getSelectedItem();
        if (c != null) {
            mostrarMensagem(c.ligar());
        } else {
            mostrarAlerta("Selecione um celular para ligar.");
        }
    }

    @FXML
    private void handleDesligar() {
        Celular c = listViewCelulares.getSelectionModel().getSelectedItem();
        if (c != null) {
            mostrarMensagem(c.desligar());
        } else {
            mostrarAlerta("Selecione um celular para desligar.");
        }
    }

    @FXML
    private void handleMostrarInfo() {
        Celular c = listViewCelulares.getSelectionModel().getSelectedItem();
        if (c != null) {
            mostrarMensagem(c.mostrarInfo());
        } else {
            mostrarAlerta("Selecione um celular para ver as informações.");
        }
    }

    private void atualizarLista() {
        celularList.setAll(celularDAO.listarTodos());
    }

    private Celular exibirDialogo(Celular existente) {
        Dialog<Celular> dialog = new Dialog<>();
        dialog.setTitle(existente == null ? "Adicionar Celular" : "Editar Celular");

        TextField marcaField = new TextField();
        TextField modeloField = new TextField();
        TextField precoField = new TextField();

        if (existente != null) {
            marcaField.setText(existente.getMarca());
            modeloField.setText(existente.getModelo());
            precoField.setText(String.valueOf(existente.getPreco()));
        }

        VBox vbox = new VBox(10,
                new Label("Marca:"), marcaField,
                new Label("Modelo:"), modeloField,
                new Label("Preço:"), precoField);

        dialog.getDialogPane().setContent(vbox);

        ButtonType salvarBtn = new ButtonType("Salvar", ButtonBar.ButtonData.OK_DONE);
        dialog.getDialogPane().getButtonTypes().addAll(salvarBtn, ButtonType.CANCEL);

        dialog.setResultConverter(bt -> {
            if (bt == salvarBtn) {
                try {
                    String marca = marcaField.getText();
                    String modelo = modeloField.getText();
                    double preco = Double.parseDouble(precoField.getText());

                    if (existente != null) {
                        return new Celular(existente.getId(), marca, modelo, preco);
                    } else {
                        return new Celular(0, marca, modelo, preco);
                    }
                } catch (NumberFormatException e) {
                    mostrarAlerta("Preço inválido.");
                }
            }
            return null;
        });

        Optional<Celular> result = dialog.showAndWait();
        return result.orElse(null);
    }

    private void mostrarAlerta(String mensagem) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Informação");
        alert.setHeaderText(null);
        alert.setContentText(mensagem);
        alert.showAndWait();
    }

    private void mostrarMensagem(String mensagem) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Ação do Celular");
        alert.setHeaderText(null);
        alert.setContentText(mensagem);
        alert.showAndWait();
    }
}
