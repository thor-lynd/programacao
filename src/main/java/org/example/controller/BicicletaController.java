package org.example.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.util.Callback;
import org.example.Classes.Bicicleta;
import org.example.dao.BicicletaDAO;

import java.util.Optional;

public class BicicletaController {

    @FXML
    private ListView<Bicicleta> listViewBicicletas;

    @FXML
    private ImageView imageViewBicicleta;

    private final BicicletaDAO bicicletaDAO = new BicicletaDAO();
    private final ObservableList<Bicicleta> bicicletaList = FXCollections.observableArrayList();

    @FXML
    public void initialize() {
        listViewBicicletas.setItems(bicicletaList);
        atualizarLista();
    }

    @FXML
    private void handleAdicionar() {
        Bicicleta novo = exibirDialogo(null);
        if (novo != null) {
            bicicletaDAO.adicionar(novo);
            atualizarLista();
        }
    }

    @FXML
    private void handleEditar() {
        Bicicleta selecionado = listViewBicicletas.getSelectionModel().getSelectedItem();
        if (selecionado != null) {
            Bicicleta editado = exibirDialogo(selecionado);
            if (editado != null) {
                bicicletaDAO.atualizar(editado);
                atualizarLista();
            }
        } else {
            mostrarAlerta("Selecione uma bicicleta para editar.");
        }
    }

    @FXML
    private void handleApagar() {
        Bicicleta selecionado = listViewBicicletas.getSelectionModel().getSelectedItem();
        if (selecionado != null) {
            bicicletaDAO.deletar(selecionado.getId());
            atualizarLista();
        } else {
            mostrarAlerta("Selecione uma bicicleta para apagar.");
        }
    }

    @FXML
    private void handleAtualizar() {
        atualizarLista();
    }

    @FXML
    private void handlePedalar() {
        Bicicleta b = listViewBicicletas.getSelectionModel().getSelectedItem();
        if (b != null) {
            mostrarMensagemMetodo("Pedalando a bicicleta modelo: " + b.getModelo(), () -> pedalar(b));
        } else {
            mostrarAlerta("Selecione uma bicicleta para pedalar.");
        }
    }

    @FXML
    private void handleFrear() {
        Bicicleta b = listViewBicicletas.getSelectionModel().getSelectedItem();
        if (b != null) {
            mostrarMensagemMetodo("Freando a bicicleta modelo: " + b.getModelo(), () -> frear(b));
        } else {
            mostrarAlerta("Selecione uma bicicleta para frear.");
        }
    }

    @FXML
    private void handleTrocarMarcha() {
        Bicicleta b = listViewBicicletas.getSelectionModel().getSelectedItem();
        if (b != null) {
            mostrarMensagemMetodo("Trocando marcha da bicicleta modelo: " + b.getModelo(), () -> trocarMarcha(b));
        } else {
            mostrarAlerta("Selecione uma bicicleta para trocar marcha.");
        }
    }

    private void atualizarLista() {
        bicicletaList.setAll(bicicletaDAO.listarTodos());
    }

    private Bicicleta exibirDialogo(Bicicleta bicicletaExistente) {
        Dialog<Bicicleta> dialog = new Dialog<>();
        dialog.setTitle(bicicletaExistente == null ? "Adicionar Bicicleta" : "Editar Bicicleta");

        TextField modeloField = new TextField();
        TextField corField = new TextField();
        TextField marchasField = new TextField();

        if (bicicletaExistente != null) {
            modeloField.setText(bicicletaExistente.getModelo());
            corField.setText(bicicletaExistente.getCor());
            marchasField.setText(String.valueOf(bicicletaExistente.getMarchas()));
        }

        VBox vbox = new VBox(10,
                new Label("Modelo:"), modeloField,
                new Label("Cor:"), corField,
                new Label("Número de marchas:"), marchasField);

        dialog.getDialogPane().setContent(vbox);

        ButtonType salvarBtn = new ButtonType("Salvar", ButtonBar.ButtonData.OK_DONE);
        dialog.getDialogPane().getButtonTypes().addAll(salvarBtn, ButtonType.CANCEL);

        dialog.setResultConverter(new Callback<ButtonType, Bicicleta>() {
            @Override
            public Bicicleta call(ButtonType buttonType) {
                if (buttonType == salvarBtn) {
                    try {
                        String modelo = modeloField.getText();
                        String cor = corField.getText();
                        int marchas = Integer.parseInt(marchasField.getText());

                        if (bicicletaExistente != null) {
                            return new Bicicleta(bicicletaExistente.getId(), modelo, cor, marchas);
                        } else {
                            return new Bicicleta(0, modelo, cor, marchas);
                        }
                    } catch (NumberFormatException e) {
                        mostrarAlerta("Número de marchas inválido.");
                    }
                }
                return null;
            }
        });

        Optional<Bicicleta> result = dialog.showAndWait();
        return result.orElse(null);
    }

    private void mostrarAlerta(String mensagem) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Informação");
        alert.setHeaderText(null);
        alert.setContentText(mensagem);
        alert.showAndWait();
    }

    private void mostrarMensagemMetodo(String mensagem, Runnable metodo) {
        metodo.run();
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Método executado");
        alert.setHeaderText(null);
        alert.setContentText(mensagem);
        alert.showAndWait();
    }

    // Métodos simulando ações da Bicicleta

    private void pedalar(Bicicleta b) {
        // lógica ou print simulando pedalada
        System.out.println("Pedalando bicicleta modelo: " + b.getModelo());
    }

    private void frear(Bicicleta b) {
        // lógica ou print simulando frenagem
        System.out.println("Freando bicicleta modelo: " + b.getModelo());
    }

    private void trocarMarcha(Bicicleta b) {
        // lógica ou print simulando troca de marcha
        System.out.println("Trocando marcha da bicicleta modelo: " + b.getModelo());
    }
}
