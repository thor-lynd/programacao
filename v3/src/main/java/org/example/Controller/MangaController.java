package org.example;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.sql.SQLException;
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

    private MangaDAO mangaDAO = new MangaDAO();
    private String nomeMangaAntigo = null;

    @FXML
    public void adicionarManga() {
        try {
            String nome = nomeManga.getText();
            int capitulo = Integer.parseInt(capituloManga.getText());
            int nota = Integer.parseInt(notaManga.getText());

            Manga manga = new Manga(nome, capitulo, nota);
            mangaDAO.inserir(manga);
            areaTextoMangas.setText("Mangá adicionado:\n" + formatarManga(manga));
            nomeMangaAntigo = nome;
            carregarImagem(nome);
            limparCampos();
        } catch (NumberFormatException e) {
            areaTextoMangas.setText("Erro: Capítulo e Nota devem ser números inteiros.");
        } catch (SQLException e) {
            areaTextoMangas.setText("Erro ao adicionar mangá: " + e.getMessage());
        }
    }

    @FXML
    public void editarManga() {
        if (nomeMangaAntigo == null) {
            areaTextoMangas.setText("Nenhum mangá selecionado para edição.");
            return;
        }

        try {
            String nome = nomeManga.getText();
            int capitulo = Integer.parseInt(capituloManga.getText());
            int nota = Integer.parseInt(notaManga.getText());

            Manga manga = new Manga(nome, capitulo, nota);
            mangaDAO.atualizar(manga, nomeMangaAntigo);
            areaTextoMangas.setText("Mangá atualizado:\n" + formatarManga(manga));
            nomeMangaAntigo = nome;
            carregarImagem(nome);
            limparCampos();
        } catch (NumberFormatException e) {
            areaTextoMangas.setText("Erro: Capítulo e Nota devem ser números inteiros.");
        } catch (SQLException e) {
            areaTextoMangas.setText("Erro ao atualizar mangá: " + e.getMessage());
        }
    }

    @FXML
    public void voltarMenu() {
        // Aqui você coloca a lógica para voltar ao menu principal,
        // por exemplo, trocar de cena ou fechar a janela.
    }

    @FXML
    public void initialize() {
        areaTextoMangas.setEditable(false);
        // Carregue uma imagem padrão (opcional)
        imagemManga.setImage(new Image("file:resources/default_manga.png"));
    }

    private String formatarManga(Manga manga) {
        return "Nome: " + manga.getNomeManga() +
                "\nCapítulo: " + manga.getCapituloManga() +
                "\nNota: " + manga.getNotaManga();
    }

    private void limparCampos() {
        nomeManga.clear();
        capituloManga.clear();
        notaManga.clear();
    }

    private void carregarImagem(String nome) {
        // Exemplo simples: carregar imagem por nome, deve existir em resources
        try {
            String caminhoImagem = "file:resources/" + nome.toLowerCase().replaceAll(" ", "_") + ".png";
            Image imagem = new Image(caminhoImagem);
            imagemManga.setImage(imagem);
        } catch (Exception e) {
            // Se não achar a imagem, pode colocar uma padrão ou limpar
            imagemManga.setImage(new Image("file:resources/default_manga.png"));
        }
    }
}
