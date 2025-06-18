package org.example.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.util.Callback;
import org.example.Classes.Carro;
import org.example.dao.CarroDAO;

import java.util.Optional;

public class CarroController {

    @FXML
    private ListView<Carro> listViewCarros;

    @FXML
    private ImageView imageViewCarro;

    private final CarroDAO carroDAO = new CarroDAO();
    private final ObservableList<Carro> carroList = FXCollections.observableArrayList();

    @FXML
    public void initialize() {
        listViewCarros.setItems(carroList);
        atualizarLista();
    }

    @FXML
    private void handleAdicionar() {
        Carro novo = exibirDialogo(null);
        if (novo != null) {
            carroDAO.adicionar(novo);
            atualizarLista();
        }
    }

    @FXML
    private void handleEditar() {
        Carro selecionado = listViewCarros.getSelectionModel().getSelectedItem();
        if (selecionado != null) {
            Carro editado = exibirDialogo(selecionado);
            if (editado != null) {
                carroDAO.atualizar(editado);
                atualizarLista();
            }
        } else {
            mostrarAlerta("Selecione um carro para editar.");
        }
    }

    @FXML
    private void handleApagar() {
        Carro selecionado = listViewCarros.getSelectionModel().getSelectedItem();
        if (selecionado != null) {
            carroDAO.deletar(selecionado.getId());
            atualizarLista();
        } else {
            mostrarAlerta("Selecione um carro para apagar.");
        }
    }

    @FXML
    private void handleAtualizar() {
        atualizarLista();
    }

    @FXML
    private void handleLigar() {
        Carro c = listViewCarros.getSelectionModel().getSelectedItem();
        if (c != null) {
            mostrarMensagemMetodo(c.getModelo(), c.getMarca(), c::ligar);
        } else {
            mostrarAlerta("Selecione um carro para ligar.");
        }
    }

    @FXML
    private void handleDesligar() {
        Carro c = listViewCarros.getSelectionModel().getSelectedItem();
        if (c != null) {
            mostrarMensagemMetodo(c.getModelo(), c.getMarca(), c::desligar);
        } else {
            mostrarAlerta("Selecione um carro para desligar.");
        }
    }

    @FXML
    private void handleAcelerar() {
        Carro c = listViewCarros.getSelectionModel().getSelectedItem();
        if (c != null) {
            mostrarMensagemMetodo(c.getModelo(), c.getMarca(), c::acelerar);
        } else {
            mostrarAlerta("Selecione um carro para acelerar.");
        }
    }

    private void atualizarLista() {
        carroList.setAll(carroDAO.listarTodos());
    }

    private Carro exibirDialogo(Carro carroExistente) {
        Dialog<Carro> dialog = new Dialog<>();
        dialog.setTitle(carroExistente == null ? "Adicionar Carro" : "Editar Carro");

        TextField modeloField = new TextField();
        TextField marcaField = new TextField();
        TextField anoField = new TextField();

        if (carroExistente != null) {
            modeloField.setText(carroExistente.getModelo());
            marcaField.setText(carroExistente.getMarca());
            anoField.setText(String.valueOf(carroExistente.getAno()));
        }

        VBox vbox = new VBox(10,
                new Label("Modelo:"), modeloField,
                new Label("Marca:"), marcaField,
                new Label("Ano:"), anoField);

        dialog.getDialogPane().setContent(vbox);

        ButtonType salvarBtn = new ButtonType("Salvar", ButtonBar.ButtonData.OK_DONE);
        dialog.getDialogPane().getButtonTypes().addAll(salvarBtn, ButtonType.CANCEL);

        dialog.setResultConverter(buttonType -> {
            if (buttonType == salvarBtn) {
                try {
                    String modelo = modeloField.getText();
                    String marca = marcaField.getText();
                    int ano = Integer.parseInt(anoField.getText());

                    if (carroExistente != null) {
                        return new Carro(carroExistente.getId(), modelo, marca, ano);
                    } else {
                        return new Carro(0, modelo, marca, ano);
                    }
                } catch (NumberFormatException e) {
                    mostrarAlerta("Ano inválido.");
                }
            }
            return null;
        });

        Optional<Carro> result = dialog.showAndWait();
        return result.orElse(null);
    }

    private void mostrarAlerta(String mensagem) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Informação");
        alert.setHeaderText(null);
        alert.setContentText(mensagem);
        alert.showAndWait();
    }

    private void mostrarMensagemMetodo(String titulo, String subtitulo, Runnable metodo) {
        metodo.run();
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Método executado");
        alert.setHeaderText(titulo);
        alert.setContentText(subtitulo);
        alert.showAndWait();
    }
}
