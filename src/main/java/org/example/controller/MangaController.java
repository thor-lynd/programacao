package org.example.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.util.Callback;
import org.example.Classes.Manga;
import org.example.dao.MangaDAO;

import java.util.Optional;

public class MangaController {

    @FXML
    private ListView<Manga> listViewMangas;

    @FXML
    private ImageView imageViewManga;

    private final MangaDAO mangaDAO = new MangaDAO();
    private final ObservableList<Manga> mangaList = FXCollections.observableArrayList();

    @FXML
    public void initialize() {
        listViewMangas.setItems(mangaList);
        atualizarLista();
    }

    @FXML
    private void handleAdicionar() {
        Manga novo = exibirDialogo(null);
        if (novo != null) {
            mangaDAO.adicionar(novo);
            atualizarLista();
        }
    }

    @FXML
    private void handleEditar() {
        Manga selecionado = listViewMangas.getSelectionModel().getSelectedItem();
        if (selecionado != null) {
            Manga editado = exibirDialogo(selecionado);
            if (editado != null) {
                mangaDAO.atualizar(editado);
                atualizarLista();
            }
        } else {
            mostrarAlerta("Selecione um mangá para editar.");
        }
    }

    @FXML
    private void handleApagar() {
        Manga selecionado = listViewMangas.getSelectionModel().getSelectedItem();
        if (selecionado != null) {
            mangaDAO.deletar(selecionado.getId());
            atualizarLista();
        } else {
            mostrarAlerta("Selecione um mangá para apagar.");
        }
    }

    @FXML
    private void handleAtualizar() {
        atualizarLista();
    }

    @FXML
    private void handleFalarNome() {
        Manga m = listViewMangas.getSelectionModel().getSelectedItem();
        if (m != null) {
            m.falarNome();
            mostrarMensagem("Nome do Mangá", m.getNome());
        } else {
            mostrarAlerta("Selecione um mangá para falar o nome.");
        }
    }

    @FXML
    private void handleFalarCapitulo() {
        Manga m = listViewMangas.getSelectionModel().getSelectedItem();
        if (m != null) {
            m.falarCapitulo();
            mostrarMensagem("Capítulo Atual", String.valueOf(m.getCapitulo()));
        } else {
            mostrarAlerta("Selecione um mangá para falar o capítulo.");
        }
    }

    @FXML
    private void handleFalarNota() {
        Manga m = listViewMangas.getSelectionModel().getSelectedItem();
        if (m != null) {
            m.falarNota();
            mostrarMensagem("Nota do Mangá", String.valueOf(m.getNota()));
        } else {
            mostrarAlerta("Selecione um mangá para falar a nota.");
        }
    }

    private void atualizarLista() {
        mangaList.setAll(mangaDAO.listarTodos());
    }

    private Manga exibirDialogo(Manga mangaExistente) {
        Dialog<Manga> dialog = new Dialog<>();
        dialog.setTitle(mangaExistente == null ? "Adicionar Mangá" : "Editar Mangá");

        TextField nomeField = new TextField();
        TextField capituloField = new TextField();
        TextField notaField = new TextField();

        if (mangaExistente != null) {
            nomeField.setText(mangaExistente.getNome());
            capituloField.setText(String.valueOf(mangaExistente.getCapitulo()));
            notaField.setText(String.valueOf(mangaExistente.getNota()));
        }

        VBox vbox = new VBox(10,
                new Label("Nome:"), nomeField,
                new Label("Capítulo:"), capituloField,
                new Label("Nota:"), notaField);

        dialog.getDialogPane().setContent(vbox);

        ButtonType salvarBtn = new ButtonType("Salvar", ButtonBar.ButtonData.OK_DONE);
        dialog.getDialogPane().getButtonTypes().addAll(salvarBtn, ButtonType.CANCEL);

        dialog.setResultConverter(buttonType -> {
            if (buttonType == salvarBtn) {
                try {
                    String nome = nomeField.getText();
                    int capitulo = Integer.parseInt(capituloField.getText());
                    int nota = Integer.parseInt(notaField.getText());

                    if (mangaExistente != null) {
                        mangaExistente.setNome(nome);
                        mangaExistente.setCapitulo(capitulo);
                        mangaExistente.setNota(nota);
                        return mangaExistente;
                    } else {
                        return new Manga(0, nome, capitulo, nota);
                    }
                } catch (NumberFormatException e) {
                    mostrarAlerta("Capítulo ou nota inválidos.");
                }
            }
            return null;
        });

        Optional<Manga> result = dialog.showAndWait();
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
