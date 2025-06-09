package org.example;

import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.util.ArrayList;
import java.util.List;

public class MangaController {

    @FXML private TextField nomeManga;
    @FXML private TextField capituloManga;
    @FXML private TextField notaManga;
    @FXML private TextArea areaTextoManga;

    private final List<Manga> listaMangas = new ArrayList<>();

    @FXML
    public void adicionarManga() {
        try {
            String nome = nomeManga.getText();
            int capitulo = Integer.parseInt(capituloManga.getText());
            int nota = Integer.parseInt(notaManga.getText());

            Manga manga = new Manga(nome, capitulo, nota);
            listaMangas.add(manga);

            limparCampos();
            areaTextoManga.setText("Mangá adicionado com sucesso!");
        } catch (NumberFormatException e) {
            areaTextoManga.setText("Erro: capítulo e nota devem ser números inteiros.");
        }
    }

    @FXML
    public void visualizarMangas() {
        if (listaMangas.isEmpty()) {
            areaTextoManga.setText("Nenhum mangá cadastrado.");
            return;
        }

        StringBuilder sb = new StringBuilder();
        for (Manga manga : listaMangas) {
            sb.append("Nome: ").append(manga.getNomeManga()).append("\n")
                    .append("Capítulo: ").append(manga.getCapituloManga()).append("\n")
                    .append("Nota: ").append(manga.getNotaManga()).append("\n")
                    .append("-------------------------\n");
        }
        areaTextoManga.setText(sb.toString());
    }

    private void limparCampos() {
        nomeManga.clear();
        capituloManga.clear();
        notaManga.clear();
    }
}
