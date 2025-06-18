package org.example.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import org.example.Classes.Pessoa;
import org.example.dao.PessoaDAO;

import java.util.Optional;

public class PessoaController {

    @FXML
    private ListView<Pessoa> listViewPessoas;

    @FXML
    private ImageView imageViewPessoa;

    private final PessoaDAO pessoaDAO = new PessoaDAO();
    private final ObservableList<Pessoa> pessoaList = FXCollections.observableArrayList();

    @FXML
    public void initialize() {
        listViewPessoas.setItems(pessoaList);
        atualizarLista();
    }

    @FXML
    private void handleAdicionar() {
        Pessoa novo = exibirDialogo(null);
        if (novo != null) {
            pessoaDAO.adicionar(novo);
            atualizarLista();
        }
    }

    @FXML
    private void handleEditar() {
        Pessoa selecionado = listViewPessoas.getSelectionModel().getSelectedItem();
        if (selecionado != null) {
            Pessoa editado = exibirDialogo(selecionado);
            if (editado != null) {
                pessoaDAO.atualizar(editado);
                atualizarLista();
            }
        } else {
            mostrarAlerta("Selecione uma pessoa para editar.");
        }
    }

    @FXML
    private void handleApagar() {
        Pessoa selecionado = listViewPessoas.getSelectionModel().getSelectedItem();
        if (selecionado != null) {
            pessoaDAO.deletar(selecionado.getId());
            atualizarLista();
        } else {
            mostrarAlerta("Selecione uma pessoa para apagar.");
        }
    }

    @FXML
    private void handleAtualizar() {
        atualizarLista();
    }

    @FXML
    private void handleFalarNome() {
        Pessoa p = listViewPessoas.getSelectionModel().getSelectedItem();
        if (p != null) {
            p.falarNome();
            mostrarMensagem("Nome", p.getNome());
        } else {
            mostrarAlerta("Selecione uma pessoa para falar o nome.");
        }
    }

    @FXML
    private void handleFalarIdade() {
        Pessoa p = listViewPessoas.getSelectionModel().getSelectedItem();
        if (p != null) {
            p.falarIdade();
            mostrarMensagem("Idade", String.valueOf(p.getIdade()));
        } else {
            mostrarAlerta("Selecione uma pessoa para falar a idade.");
        }
    }

    @FXML
    private void handleFalarOcupacao() {
        Pessoa p = listViewPessoas.getSelectionModel().getSelectedItem();
        if (p != null) {
            p.falarOcupacao();
            mostrarMensagem("Ocupação", p.getOcupacao());
        } else {
            mostrarAlerta("Selecione uma pessoa para falar a ocupação.");
        }
    }

    private void atualizarLista() {
        pessoaList.setAll(pessoaDAO.listarTodos());
    }

    private Pessoa exibirDialogo(Pessoa pessoaExistente) {
        Dialog<Pessoa> dialog = new Dialog<>();
        dialog.setTitle(pessoaExistente == null ? "Adicionar Pessoa" : "Editar Pessoa");

        TextField nomeField = new TextField();
        TextField idadeField = new TextField();
        TextField ocupacaoField = new TextField();

        if (pessoaExistente != null) {
            nomeField.setText(pessoaExistente.getNome());
            idadeField.setText(String.valueOf(pessoaExistente.getIdade()));
            ocupacaoField.setText(pessoaExistente.getOcupacao());
        }

        VBox vbox = new VBox(10,
                new Label("Nome:"), nomeField,
                new Label("Idade:"), idadeField,
                new Label("Ocupação:"), ocupacaoField);

        dialog.getDialogPane().setContent(vbox);

        ButtonType salvarBtn = new ButtonType("Salvar", ButtonBar.ButtonData.OK_DONE);
        dialog.getDialogPane().getButtonTypes().addAll(salvarBtn, ButtonType.CANCEL);

        dialog.setResultConverter(buttonType -> {
            if (buttonType == salvarBtn) {
                try {
                    String nome = nomeField.getText();
                    int idade = Integer.parseInt(idadeField.getText());
                    String ocupacao = ocupacaoField.getText();

                    if (pessoaExistente != null) {
                        pessoaExistente.setNome(nome);
                        pessoaExistente.setIdade(idade);
                        pessoaExistente.setOcupacao(ocupacao);
                        return pessoaExistente;
                    } else {
                        return new Pessoa(0, nome, idade, ocupacao);
                    }
                } catch (NumberFormatException e) {
                    mostrarAlerta("Idade inválida.");
                }
            }
            return null;
        });

        Optional<Pessoa> result = dialog.showAndWait();
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
