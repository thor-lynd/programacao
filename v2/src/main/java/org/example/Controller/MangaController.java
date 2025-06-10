package org.example;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MangaController {

    @FXML
    private TextField nomeManga;

    @FXML
    private TextField capituloManga;

    @FXML
    private TextField notaManga;

    @FXML
    private TextArea areaTextoMangas;

    @FXML
    private ImageView imagemManga;

    private List<Manga> mangas = new ArrayList<>();

    private int indiceEdicao = -1;

    @FXML
    private Button btnAdicionar;

    @FXML
    private Button btnEditar;

    @FXML
    private Button btnVoltar;

    @FXML
    private void adicionarManga() {
        String nome = nomeManga.getText().trim();
        String capituloStr = capituloManga.getText().trim();
        String notaStr = notaManga.getText().trim();

        if (nome.isEmpty() || capituloStr.isEmpty() || notaStr.isEmpty()) {
            alert("Preencha todos os campos.");
            return;
        }

        int capitulo, nota;
        try {
            capitulo = Integer.parseInt(capituloStr);
            nota = Integer.parseInt(notaStr);
        } catch (NumberFormatException e) {
            alert("Capítulo e nota devem ser números inteiros.");
            return;
        }

        if (indiceEdicao >= 0) {
            mangas.set(indiceEdicao, new Manga(nome, capitulo, nota));
            indiceEdicao = -1;
            btnAdicionar.setText("Adicionar Mangá");
        } else {
            mangas.add(new Manga(nome, capitulo, nota));
        }

        limparCampos();
        visualizarMangas();
    }

    @FXML
    private void visualizarMangas() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < mangas.size(); i++) {
            Manga m = mangas.get(i);
            sb.append(i).append(": ")
                    .append("Nome: ").append(m.getNomeManga())
                    .append(", Capítulo: ").append(m.getCapituloManga())
                    .append(", Nota: ").append(m.getNotaManga())
                    .append("\n");
        }
        areaTextoMangas.setText(sb.toString());
    }

    @FXML
    private void editarManga() {
        String texto = areaTextoMangas.getSelectedText();
        if (texto.isEmpty()) {
            alert("Selecione um mangá para editar na área de texto.");
            return;
        }

        int index = -1;
        try {
            index = Integer.parseInt(texto.split(":")[0].trim());
        } catch (Exception e) {
            alert("Seleção inválida. Selecione o número do mangá no começo da linha.");
            return;
        }

        if (index < 0 || index >= mangas.size()) {
            alert("Índice inválido.");
            return;
        }

        Manga m = mangas.get(index);
        nomeManga.setText(m.getNomeManga());
        capituloManga.setText(String.valueOf(m.getCapituloManga()));
        notaManga.setText(String.valueOf(m.getNotaManga()));

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
        nomeManga.clear();
        capituloManga.clear();
        notaManga.clear();
        indiceEdicao = -1;
        btnAdicionar.setText("Adicionar Mangá");
    }

    private void alert(String msg) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Atenção");
        alert.setContentText(msg);
        alert.showAndWait();
    }
}
