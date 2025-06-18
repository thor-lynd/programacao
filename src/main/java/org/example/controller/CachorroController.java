package org.example.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.util.Callback;
import org.example.Classes.Cachorro;
import org.example.dao.CachorroDAO;

import java.util.Optional;

public class CachorroController {

    @FXML
    private ListView<Cachorro> listViewCachorros;

    @FXML
    private ImageView imageViewCachorro;

    private final CachorroDAO cachorroDAO = new CachorroDAO();
    private final ObservableList<Cachorro> cachorroList = FXCollections.observableArrayList();

    @FXML
    public void initialize() {
        listViewCachorros.setItems(cachorroList);
        atualizarLista();
    }

    @FXML
    private void handleAdicionar() {
        Cachorro novo = exibirDialogo(null);
        if (novo != null) {
            cachorroDAO.adicionar(novo);
            atualizarLista();
        }
    }

    @FXML
    private void handleEditar() {
        Cachorro selecionado = listViewCachorros.getSelectionModel().getSelectedItem();
        if (selecionado != null) {
            Cachorro editado = exibirDialogo(selecionado);
            if (editado != null) {
                cachorroDAO.atualizar(editado);
                atualizarLista();
            }
        } else {
            mostrarAlerta("Selecione um cachorro para editar.");
        }
    }

    @FXML
    private void handleApagar() {
        Cachorro selecionado = listViewCachorros.getSelectionModel().getSelectedItem();
        if (selecionado != null) {
            cachorroDAO.deletar(selecionado.getId());
            atualizarLista();
        } else {
            mostrarAlerta("Selecione um cachorro para apagar.");
        }
    }

    @FXML
    private void handleAtualizar() {
        atualizarLista();
    }

    @FXML
    private void handleLatir() {
        Cachorro c = listViewCachorros.getSelectionModel().getSelectedItem();
        if (c != null) {
            mostrarMensagemMetodo(c.getNome(), "", c::latir);
        } else {
            mostrarAlerta("Selecione um cachorro para latir.");
        }
    }

    @FXML
    private void handleFalarIdade() {
        Cachorro c = listViewCachorros.getSelectionModel().getSelectedItem();
        if (c != null) {
            mostrarMensagemMetodo(c.getNome(), "Idade: " + c.getIdade(), () -> {});
        } else {
            mostrarAlerta("Selecione um cachorro para falar a idade.");
        }
    }

    @FXML
    private void handleFalarRaca() {
        Cachorro c = listViewCachorros.getSelectionModel().getSelectedItem();
        if (c != null) {
            mostrarMensagemMetodo(c.getNome(), "Raça: " + c.getRaca(), () -> {});
        } else {
            mostrarAlerta("Selecione um cachorro para falar a raça.");
        }
    }

    private void atualizarLista() {
        cachorroList.setAll(cachorroDAO.listarTodos());
    }

    private Cachorro exibirDialogo(Cachorro cachorroExistente) {
        Dialog<Cachorro> dialog = new Dialog<>();
        dialog.setTitle(cachorroExistente == null ? "Adicionar Cachorro" : "Editar Cachorro");

        TextField nomeField = new TextField();
        TextField idadeField = new TextField();
        TextField racaField = new TextField();

        if (cachorroExistente != null) {
            nomeField.setText(cachorroExistente.getNome());
            idadeField.setText(String.valueOf(cachorroExistente.getIdade()));
            racaField.setText(cachorroExistente.getRaca());
        }

        VBox vbox = new VBox(10,
                new Label("Nome:"), nomeField,
                new Label("Idade:"), idadeField,
                new Label("Raça:"), racaField);

        dialog.getDialogPane().setContent(vbox);

        ButtonType salvarBtn = new ButtonType("Salvar", ButtonBar.ButtonData.OK_DONE);
        dialog.getDialogPane().getButtonTypes().addAll(salvarBtn, ButtonType.CANCEL);

        dialog.setResultConverter(new Callback<ButtonType, Cachorro>() {
            @Override
            public Cachorro call(ButtonType buttonType) {
                if (buttonType == salvarBtn) {
                    try {
                        String nome = nomeField.getText();
                        int idade = Integer.parseInt(idadeField.getText());
                        String raca = racaField.getText();

                        if (cachorroExistente != null) {
                            return new Cachorro(cachorroExistente.getId(), nome, raca, idade);
                        } else {
                            return new Cachorro(0, nome, raca, idade);
                        }
                    } catch (NumberFormatException e) {
                        mostrarAlerta("Idade inválida.");
                    }
                }
                return null;
            }
        });

        Optional<Cachorro> result = dialog.showAndWait();
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
