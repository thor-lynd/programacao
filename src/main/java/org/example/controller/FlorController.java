package org.example.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.util.Callback;
import org.example.Classes.Flor;
import org.example.dao.FlorDAO;

import java.util.Optional;

public class FlorController {

    @FXML private ListView<Flor> listViewFlores;
    @FXML private ImageView imageViewFlor;

    private final FlorDAO florDAO = new FlorDAO();
    private final ObservableList<Flor> florList = FXCollections.observableArrayList();

    @FXML
    public void initialize() {
        listViewFlores.setItems(florList);
        atualizarLista();
    }

    @FXML
    private void handleAdicionar() {
        Flor nova = exibirDialogo(null);
        if (nova != null) {
            florDAO.adicionar(nova);
            atualizarLista();
        }
    }

    @FXML
    private void handleEditar() {
        Flor selecionada = listViewFlores.getSelectionModel().getSelectedItem();
        if (selecionada != null) {
            Flor editada = exibirDialogo(selecionada);
            if (editada != null) {
                florDAO.atualizar(editada);
                atualizarLista();
            }
        } else {
            mostrarAlerta("Selecione uma flor para editar.");
        }
    }

    @FXML
    private void handleApagar() {
        Flor selecionada = listViewFlores.getSelectionModel().getSelectedItem();
        if (selecionada != null) {
            florDAO.deletar(selecionada.getId());
            atualizarLista();
        } else {
            mostrarAlerta("Selecione uma flor para apagar.");
        }
    }

    @FXML
    private void handleAtualizar() {
        atualizarLista();
    }

    @FXML
    private void handleFlorescer() {
        Flor flor = listViewFlores.getSelectionModel().getSelectedItem();
        if (flor != null) {
            flor.florescer();
        } else {
            mostrarAlerta("Selecione uma flor para florescer.");
        }
    }

    @FXML
    private void handleCrescer() {
        Flor flor = listViewFlores.getSelectionModel().getSelectedItem();
        if (flor != null) {
            flor.crescer(5); // Cresce 5 cm por padrão
        } else {
            mostrarAlerta("Selecione uma flor para crescer.");
        }
    }

    @FXML
    private void handleMurchar() {
        Flor flor = listViewFlores.getSelectionModel().getSelectedItem();
        if (flor != null) {
            flor.murchar();
        } else {
            mostrarAlerta("Selecione uma flor para murchar.");
        }
    }

    private void atualizarLista() {
        florList.setAll(florDAO.listarTodos());
    }

    private Flor exibirDialogo(Flor florExistente) {
        Dialog<Flor> dialog = new Dialog<>();
        dialog.setTitle(florExistente == null ? "Adicionar Flor" : "Editar Flor");

        TextField nomeField = new TextField();
        TextField corField = new TextField();
        TextField alturaField = new TextField();

        if (florExistente != null) {
            nomeField.setText(florExistente.getNome());
            corField.setText(florExistente.getCor());
            alturaField.setText(String.valueOf(florExistente.getAltura()));
        }

        VBox vbox = new VBox(10,
                new Label("Nome:"), nomeField,
                new Label("Cor:"), corField,
                new Label("Altura (cm):"), alturaField);

        dialog.getDialogPane().setContent(vbox);

        ButtonType salvarBtn = new ButtonType("Salvar", ButtonBar.ButtonData.OK_DONE);
        dialog.getDialogPane().getButtonTypes().addAll(salvarBtn, ButtonType.CANCEL);

        dialog.setResultConverter(buttonType -> {
            if (buttonType == salvarBtn) {
                try {
                    String nome = nomeField.getText();
                    String cor = corField.getText();
                    int altura = Integer.parseInt(alturaField.getText());

                    if (florExistente != null) {
                        return new Flor(florExistente.getId(), nome, cor, altura);
                    } else {
                        return new Flor(0, nome, cor, altura);
                    }
                } catch (NumberFormatException e) {
                    mostrarAlerta("Altura inválida.");
                }
            }
            return null;
        });

        Optional<Flor> result = dialog.showAndWait();
        return result.orElse(null);
    }

    private void mostrarAlerta(String mensagem) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Informação");
        alert.setHeaderText(null);
        alert.setContentText(mensagem);
        alert.showAndWait();
    }
}
