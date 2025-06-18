package org.example.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.util.Callback;
import org.example.Classes.Peixe;
import org.example.dao.PeixeDAO;

import java.util.Optional;

public class PeixeController {

    @FXML
    private ListView<Peixe> listViewPeixes;

    @FXML
    private ImageView imageViewPeixe;

    private final PeixeDAO peixeDAO = new PeixeDAO();
    private final ObservableList<Peixe> peixeList = FXCollections.observableArrayList();

    @FXML
    public void initialize() {
        listViewPeixes.setItems(peixeList);
        atualizarLista();
    }

    @FXML
    private void handleAdicionar() {
        Peixe novo = exibirDialogo(null);
        if (novo != null) {
            peixeDAO.adicionar(novo);
            atualizarLista();
        }
    }

    @FXML
    private void handleEditar() {
        Peixe selecionado = listViewPeixes.getSelectionModel().getSelectedItem();
        if (selecionado != null) {
            Peixe editado = exibirDialogo(selecionado);
            if (editado != null) {
                peixeDAO.atualizar(editado);
                atualizarLista();
            }
        } else {
            mostrarAlerta("Selecione um peixe para editar.");
        }
    }

    @FXML
    private void handleApagar() {
        Peixe selecionado = listViewPeixes.getSelectionModel().getSelectedItem();
        if (selecionado != null) {
            peixeDAO.deletar(selecionado.getId());
            atualizarLista();
        } else {
            mostrarAlerta("Selecione um peixe para apagar.");
        }
    }

    @FXML
    private void handleAtualizar() {
        atualizarLista();
    }

    @FXML
    private void handleNomear() {
        Peixe p = listViewPeixes.getSelectionModel().getSelectedItem();
        if (p != null) {
            p.nomear();
            mostrarMensagem("Nome do Peixe", p.getNome());
        } else {
            mostrarAlerta("Selecione um peixe para nomear.");
        }
    }

    @FXML
    private void handleEspecificar() {
        Peixe p = listViewPeixes.getSelectionModel().getSelectedItem();
        if (p != null) {
            p.especificar();
            mostrarMensagem("Tipo do Peixe", p.getTipo());
        } else {
            mostrarAlerta("Selecione um peixe para especificar.");
        }
    }

    @FXML
    private void handleContar() {
        Peixe p = listViewPeixes.getSelectionModel().getSelectedItem();
        if (p != null) {
            p.contar();
            mostrarMensagem("Quantidade de Peixes", String.valueOf(p.getQuantidade()));
        } else {
            mostrarAlerta("Selecione um peixe para contar.");
        }
    }

    private void atualizarLista() {
        peixeList.setAll(peixeDAO.listarTodos());
    }

    private Peixe exibirDialogo(Peixe peixeExistente) {
        Dialog<Peixe> dialog = new Dialog<>();
        dialog.setTitle(peixeExistente == null ? "Adicionar Peixe" : "Editar Peixe");

        TextField nomeField = new TextField();
        TextField tipoField = new TextField();
        TextField quantidadeField = new TextField();

        if (peixeExistente != null) {
            nomeField.setText(peixeExistente.getNome());
            tipoField.setText(peixeExistente.getTipo());
            quantidadeField.setText(String.valueOf(peixeExistente.getQuantidade()));
        }

        VBox vbox = new VBox(10,
                new Label("Nome:"), nomeField,
                new Label("Tipo:"), tipoField,
                new Label("Quantidade:"), quantidadeField);

        dialog.getDialogPane().setContent(vbox);

        ButtonType salvarBtn = new ButtonType("Salvar", ButtonBar.ButtonData.OK_DONE);
        dialog.getDialogPane().getButtonTypes().addAll(salvarBtn, ButtonType.CANCEL);

        dialog.setResultConverter(buttonType -> {
            if (buttonType == salvarBtn) {
                try {
                    String nome = nomeField.getText();
                    String tipo = tipoField.getText();
                    int quantidade = Integer.parseInt(quantidadeField.getText());

                    if (peixeExistente != null) {
                        peixeExistente.setNome(nome);
                        peixeExistente.setTipo(tipo);
                        peixeExistente.setQuantidade(quantidade);
                        return peixeExistente;
                    } else {
                        return new Peixe(0, nome, tipo, quantidade);
                    }
                } catch (NumberFormatException e) {
                    mostrarAlerta("Quantidade inválida.");
                }
            }
            return null;
        });

        Optional<Peixe> result = dialog.showAndWait();
        return result.orElse(null);
    }

    private void mostrarAlerta(String mensagem) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Informação");
        alert.setHeaderText(null);
        alert.setContentText(mensagem);
        alert.showAndWait();
    }

    private void mostrarMensagem(String titulo, String mensagem) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensagem);
        alert.showAndWait();
    }
}
