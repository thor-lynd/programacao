package org.example.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import org.example.Classes.Livro;
import org.example.dao.LivroDAO;

import java.util.Optional;

public class LivroController {

    @FXML private ListView<Livro> listViewLivros;
    @FXML private ImageView imageViewLivro;

    private final LivroDAO livroDAO = new LivroDAO();
    private final ObservableList<Livro> livroList = FXCollections.observableArrayList();

    @FXML
    public void initialize() {
        listViewLivros.setItems(livroList);
        atualizarLista();
    }

    @FXML
    private void handleAdicionar() {
        Livro novo = exibirDialogo(null);
        if (novo != null) {
            livroDAO.adicionar(novo);
            atualizarLista();
        }
    }

    @FXML
    private void handleEditar() {
        Livro selecionado = listViewLivros.getSelectionModel().getSelectedItem();
        if (selecionado != null) {
            Livro editado = exibirDialogo(selecionado);
            if (editado != null) {
                livroDAO.atualizar(editado);
                atualizarLista();
            }
        } else {
            mostrarAlerta("Selecione um livro para editar.");
        }
    }

    @FXML
    private void handleApagar() {
        Livro selecionado = listViewLivros.getSelectionModel().getSelectedItem();
        if (selecionado != null) {
            livroDAO.deletar(selecionado.getId());
            atualizarLista();
        } else {
            mostrarAlerta("Selecione um livro para apagar.");
        }
    }

    @FXML
    private void handleAtualizar() {
        atualizarLista();
    }

    @FXML
    private void handleAbrir() {
        Livro livro = listViewLivros.getSelectionModel().getSelectedItem();
        if (livro != null) {
            livro.abrir();
        } else {
            mostrarAlerta("Selecione um livro para abrir.");
        }
    }

    @FXML
    private void handleFechar() {
        Livro livro = listViewLivros.getSelectionModel().getSelectedItem();
        if (livro != null) {
            livro.fechar();
        } else {
            mostrarAlerta("Selecione um livro para fechar.");
        }
    }

    @FXML
    private void handleLerPagina() {
        Livro livro = listViewLivros.getSelectionModel().getSelectedItem();
        if (livro != null) {
            TextInputDialog dialog = new TextInputDialog();
            dialog.setTitle("Ler Página");
            dialog.setHeaderText("Digite o número da página:");
            dialog.setContentText("Página:");

            Optional<String> result = dialog.showAndWait();
            result.ifPresent(paginaStr -> {
                try {
                    int pagina = Integer.parseInt(paginaStr);
                    livro.lerPagina(pagina);
                } catch (NumberFormatException e) {
                    mostrarAlerta("Número de página inválido.");
                }
            });
        } else {
            mostrarAlerta("Selecione um livro para ler.");
        }
    }

    private void atualizarLista() {
        livroList.setAll(livroDAO.listarTodos());
    }

    private Livro exibirDialogo(Livro livroExistente) {
        Dialog<Livro> dialog = new Dialog<>();
        dialog.setTitle(livroExistente == null ? "Adicionar Livro" : "Editar Livro");

        TextField tituloField = new TextField();
        TextField autorField = new TextField();
        TextField paginasField = new TextField();

        if (livroExistente != null) {
            tituloField.setText(livroExistente.getTitulo());
            autorField.setText(livroExistente.getAutor());
            paginasField.setText(String.valueOf(livroExistente.getPaginas()));
        }

        VBox vbox = new VBox(10,
                new Label("Título:"), tituloField,
                new Label("Autor:"), autorField,
                new Label("Páginas:"), paginasField);

        dialog.getDialogPane().setContent(vbox);

        ButtonType salvarBtn = new ButtonType("Salvar", ButtonBar.ButtonData.OK_DONE);
        dialog.getDialogPane().getButtonTypes().addAll(salvarBtn, ButtonType.CANCEL);

        dialog.setResultConverter(buttonType -> {
            if (buttonType == salvarBtn) {
                try {
                    String titulo = tituloField.getText();
                    String autor = autorField.getText();
                    int paginas = Integer.parseInt(paginasField.getText());

                    if (livroExistente != null) {
                        return new Livro(livroExistente.getId(), titulo, autor, paginas);
                    } else {
                        return new Livro(0, titulo, autor, paginas);
                    }
                } catch (NumberFormatException e) {
                    mostrarAlerta("Número de páginas inválido.");
                }
            }
            return null;
        });

        Optional<Livro> result = dialog.showAndWait();
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
