package org.example;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;

import java.sql.SQLException;
import java.util.List;

public class AlbumController {

    @FXML
    private TextField nomeAlbum;

    @FXML
    private TextField musicasAlbum;

    @FXML
    private TextField artistaAlbum;

    @FXML
    private TextArea areaTextoAlbuns;

    @FXML
    private ImageView imagemAlbum;  // Se quiser usar futuramente para mostrar capa

    private AlbumDAO dao = new AlbumDAO();

    @FXML
    private void adicionarAlbum() {
        try {
            String nome = nomeAlbum.getText();
            int musicas = Integer.parseInt(musicasAlbum.getText());
            String artista = artistaAlbum.getText();

            Album novo = new Album(nome, musicas, artista);
            dao.inserir(novo);

            limparCampos();
            atualizarLista();

            mostrarAlerta(Alert.AlertType.INFORMATION, "Sucesso", "Álbum adicionado!");
        } catch (NumberFormatException e) {
            mostrarAlerta(Alert.AlertType.ERROR, "Erro", "Número de músicas inválido.");
        } catch (SQLException e) {
            mostrarAlerta(Alert.AlertType.ERROR, "Erro de banco", e.getMessage());
        }
    }

    @FXML
    private void editarAlbum() {
        // Se quiser implementar edição, pode ser por ID, ou lista com seleção
        mostrarAlerta(Alert.AlertType.INFORMATION, "Info", "Editar não implementado ainda.");
    }

    @FXML
    private void voltarMenu() {
        // Lógica para voltar ao menu principal, se tiver
        System.out.println("Voltar ao menu (implementar).");
    }

    @FXML
    private void initialize() {
        atualizarLista();
    }

    private void atualizarLista() {
        try {
            List<Album> albuns = dao.listar();
            StringBuilder sb = new StringBuilder();
            for (Album a : albuns) {
                sb.append("ID: ").append(a.getId())
                        .append(" | Nome: ").append(a.getNome())
                        .append(" | Músicas: ").append(a.getMusicas())
                        .append(" | Artista: ").append(a.getArtista())
                        .append("\n");
            }
            areaTextoAlbuns.setText(sb.toString());
        } catch (SQLException e) {
            mostrarAlerta(Alert.AlertType.ERROR, "Erro de banco", e.getMessage());
        }
    }

    private void limparCampos() {
        nomeAlbum.clear();
        musicasAlbum.clear();
        artistaAlbum.clear();
    }

    private void mostrarAlerta(Alert.AlertType tipo, String titulo, String mensagem) {
        Alert alert = new Alert(tipo);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensagem);
        alert.showAndWait();
    }
}
