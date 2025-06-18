package org.example.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.util.Callback;
import org.example.Classes.Album;
import org.example.dao.AlbumDAO;

import java.util.Optional;

public class AlbumController {

    @FXML
    private ListView<Album> listViewAlbums;

    @FXML
    private ImageView imageViewAlbum;

    private final AlbumDAO albumDAO = new AlbumDAO();
    private final ObservableList<Album> albumList = FXCollections.observableArrayList();

    @FXML
    public void initialize() {
        listViewAlbums.setItems(albumList);
        atualizarLista();
    }

    @FXML
    private void handleAdicionar() {
        Album novo = exibirDialogo(null);
        if (novo != null) {
            albumDAO.adicionar(novo);
            atualizarLista();
        }
    }

    @FXML
    private void handleEditar() {
        Album selecionado = listViewAlbums.getSelectionModel().getSelectedItem();
        if (selecionado != null) {
            Album editado = exibirDialogo(selecionado);
            if (editado != null) {
                albumDAO.atualizar(editado);
                atualizarLista();
            }
        } else {
            mostrarAlerta("Selecione um álbum para editar.");
        }
    }

    @FXML
    private void handleApagar() {
        Album selecionado = listViewAlbums.getSelectionModel().getSelectedItem();
        if (selecionado != null) {
            albumDAO.deletar(selecionado.getId());
            atualizarLista();
        } else {
            mostrarAlerta("Selecione um álbum para apagar.");
        }
    }

    @FXML
    private void handleAtualizar() {
        atualizarLista();
    }

    @FXML
    private void handleEscutar() {
        Album a = listViewAlbums.getSelectionModel().getSelectedItem();
        if (a != null) {
            mostrarMensagemMetodo(a.getNome(), a.getArtista(), a::escutar);
        } else {
            mostrarAlerta("Selecione um álbum para escutar.");
        }
    }

    @FXML
    private void handleSkippar() {
        Album a = listViewAlbums.getSelectionModel().getSelectedItem();
        if (a != null) {
            mostrarMensagemMetodo(a.getNome(), "", a::skippar);
        } else {
            mostrarAlerta("Selecione um álbum para skippar.");
        }
    }

    @FXML
    private void handleAcabar() {
        Album a = listViewAlbums.getSelectionModel().getSelectedItem();
        if (a != null) {
            mostrarMensagemMetodo(a.getNome(), "", a::acabar);
        } else {
            mostrarAlerta("Selecione um álbum para finalizar.");
        }
    }

    private void atualizarLista() {
        albumList.setAll(albumDAO.listarTodos());
    }

    private Album exibirDialogo(Album albumExistente) {
        Dialog<Album> dialog = new Dialog<>();
        dialog.setTitle(albumExistente == null ? "Adicionar Álbum" : "Editar Álbum");

        TextField nomeField = new TextField();
        TextField musicasField = new TextField();
        TextField artistaField = new TextField();

        if (albumExistente != null) {
            nomeField.setText(albumExistente.getNome());
            musicasField.setText(String.valueOf(albumExistente.getMusicas()));
            artistaField.setText(albumExistente.getArtista());
        }

        VBox vbox = new VBox(10,
                new Label("Nome:"), nomeField,
                new Label("Quantidade de músicas:"), musicasField,
                new Label("Artista:"), artistaField);

        dialog.getDialogPane().setContent(vbox);

        ButtonType salvarBtn = new ButtonType("Salvar", ButtonBar.ButtonData.OK_DONE);
        dialog.getDialogPane().getButtonTypes().addAll(salvarBtn, ButtonType.CANCEL);

        dialog.setResultConverter(new Callback<ButtonType, Album>() {
            @Override
            public Album call(ButtonType buttonType) {
                if (buttonType == salvarBtn) {
                    try {
                        String nome = nomeField.getText();
                        int musicas = Integer.parseInt(musicasField.getText());
                        String artista = artistaField.getText();

                        if (albumExistente != null) {
                            return new Album(albumExistente.getId(), nome, musicas, artista);
                        } else {
                            return new Album(0, nome, musicas, artista);
                        }
                    } catch (NumberFormatException e) {
                        mostrarAlerta("Número de músicas inválido.");
                    }
                }
                return null;
            }
        });

        Optional<Album> result = dialog.showAndWait();
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
