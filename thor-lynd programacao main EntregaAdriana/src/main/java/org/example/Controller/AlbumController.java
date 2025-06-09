package org.example;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;

import java.io.IOException;
import java.util.ArrayList;
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
    private ImageView imagemAlbum;

    private List<Album> albuns = new ArrayList<>();

    private int indiceEdicao = -1; // controla se está editando algum álbum (-1 = não)

    @FXML
    private Button btnAdicionar;

    @FXML
    private Button btnVisualizar;

    @FXML
    private Button btnEditar;

    @FXML
    private Button btnVoltar;

    @FXML
    private void initialize() {
        // Inicializações se precisar
    }

    @FXML
    private void adicionarAlbum() {
        String nome = nomeAlbum.getText().trim();
        String musicasStr = musicasAlbum.getText().trim();
        String artista = artistaAlbum.getText().trim();

        if (nome.isEmpty() || musicasStr.isEmpty() || artista.isEmpty()) {
            alert("Preencha todos os campos.");
            return;
        }

        int musicas;
        try {
            musicas = Integer.parseInt(musicasStr);
        } catch (NumberFormatException e) {
            alert("Número de músicas inválido.");
            return;
        }

        if (indiceEdicao >= 0) {
            // Editar álbum existente
            Album album = albuns.get(indiceEdicao);
            albuns.set(indiceEdicao, new Album(nome, musicas, artista));
            indiceEdicao = -1;
            btnAdicionar.setText("Adicionar Álbum");
        } else {
            // Adicionar novo álbum
            Album album = new Album(nome, musicas, artista);
            albuns.add(album);
        }

        limparCampos();
        visualizarAlbuns();
    }

    @FXML
    private void visualizarAlbuns() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < albuns.size(); i++) {
            Album a = albuns.get(i);
            sb.append(i).append(": ")
                    .append("Nome: ").append(a.getNome())
                    .append(", Músicas: ").append(a.getMusicas())
                    .append(", Artista: ").append(a.getArtista())
                    .append("\n");
        }
        areaTextoAlbuns.setText(sb.toString());
    }

    @FXML
    private void editarAlbum() {
        String texto = areaTextoAlbuns.getSelectedText();
        if (texto.isEmpty()) {
            alert("Selecione um álbum para editar na área de texto.");
            return;
        }

        // Tentar extrair índice pelo formato "índice: ..."
        int index = -1;
        try {
            String[] parts = texto.split(":");
            index = Integer.parseInt(parts[0].trim());
        } catch (Exception e) {
            alert("Seleção inválida. Selecione o número do álbum no começo da linha.");
            return;
        }

        if (index < 0 || index >= albuns.size()) {
            alert("Índice inválido.");
            return;
        }

        Album album = albuns.get(index);
        nomeAlbum.setText(album.getNome());
        musicasAlbum.setText(String.valueOf(album.getMusicas()));
        artistaAlbum.setText(album.getArtista());

        indiceEdicao = index;
        btnAdicionar.setText("Salvar Alterações");
    }

    @FXML
    private void voltarMenu() {
        try {
            App.setRoot("menu");
        } catch (IOException e) {
            e.printStackTrace();
            alert("Erro ao voltar para o menu.");
        }
    }

    private void limparCampos() {
        nomeAlbum.clear();
        musicasAlbum.clear();
        artistaAlbum.clear();
        indiceEdicao = -1;
        btnAdicionar.setText("Adicionar Álbum");
    }

    private void alert(String msg) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Atenção");
        alert.setContentText(msg);
        alert.showAndWait();
    }
}
