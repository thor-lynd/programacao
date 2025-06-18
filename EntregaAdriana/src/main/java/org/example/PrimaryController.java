package org.example;

import java.io.IOException;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class PrimaryController {

    // CELULAR
    @FXML
    private TextField marcaCelular;
    @FXML
    private TextField modeloCelular;
    @FXML
    private TextField precoCelular;
    @FXML
    private Button salvarCelular;

    public void adicionarCelular() throws IOException {
        String marca = marcaCelular.getText();
        String modelo = modeloCelular.getText();
        double preco = Double.parseDouble(precoCelular.getText());

        Celulares celular = new Celulares(marca, modelo, preco);

        FXMLLoader loader = new FXMLLoader(getClass().getResource("secondary.fxml"));
        Parent root = loader.load();

        SecondaryController controller = loader.getController();
        controller.setCelular(celular);

        Stage stage = (Stage) marcaCelular.getScene().getWindow();
        stage.setScene(new Scene(root));
    }
    // FIM CELULAR

    // ALBUM
    @FXML
    private TextField nomeAlbum;
    @FXML
    private TextField quantidadeAlbum;
    @FXML
    private TextField artistaAlbum;
    @FXML
    private Button salvarAlbum;

    public void adicionarAlbum() throws IOException {
        String nome = nomeAlbum.getText();
        int musicas = Integer.parseInt(quantidadeAlbum.getText());
        String artista = artistaAlbum.getText();

        Albums album = new Albums(nome, musicas, artista);

        FXMLLoader loader = new FXMLLoader(getClass().getResource("secondary.fxml"));
        Parent root = loader.load();

        SecondaryController controller = loader.getController();
        controller.setAlbum(album);

        Stage stage = (Stage) nomeAlbum.getScene().getWindow();
        stage.setScene(new Scene(root));
    }
    // FIM ALBUM

    // PESSOAS
    @FXML
    private TextField nomePessoa;
    @FXML
    private TextField idadePessoa;
    @FXML
    private TextField ocupacaoPessoa;
    @FXML
    private Button salvarPessoa;

    public void adicionarPessoa() throws IOException {
        String nome = nomePessoa.getText();
        int idade = Integer.parseInt(idadePessoa.getText());
        String ocupacao = ocupacaoPessoa.getText();

        Pessoas pessoa = new Pessoas(nome, idade, ocupacao);

        FXMLLoader loader = new FXMLLoader(getClass().getResource("secondary.fxml"));
        Parent root = loader.load();

        SecondaryController controller = loader.getController();
        controller.setPessoa(pessoa);

        Stage stage = (Stage) nomePessoa.getScene().getWindow();
        stage.setScene(new Scene(root));
    }
    // FIM PESSOAS

    // CARROS
    @FXML
    private TextField modeloCarro;
    @FXML
    private TextField marcaCarro;
    @FXML
    private TextField anoCarro;
    @FXML
    private Button salvarCarro;

    public void adicionarCarro() throws IOException {
        String modelo = modeloCarro.getText();
        String marca = marcaCarro.getText();
        int ano = Integer.parseInt(anoCarro.getText());

        Carros carro = new Carros(modelo, marca, ano);

        FXMLLoader loader = new FXMLLoader(getClass().getResource("secondary.fxml"));
        Parent root = loader.load();

        SecondaryController controller = loader.getController();
        controller.setCarro(carro);

        Stage stage = (Stage) modeloCarro.getScene().getWindow();
        stage.setScene(new Scene(root));
    }
    // FIM CARROS

    // TENIS
    @FXML
    private TextField marcaTenis;
    @FXML
    private TextField corTenis;
    @FXML
    private TextField tamanhoTenis;
    @FXML
    private Button salvarTenis;

    public void adicionarTenis() throws IOException {
        String marca = marcaTenis.getText();
        String cor = corTenis.getText();
        int tamanho = Integer.parseInt(tamanhoTenis.getText());

        Tenis tenis = new Tenis(marca, cor, tamanho);

        FXMLLoader loader = new FXMLLoader(getClass().getResource("secondary.fxml"));
        Parent root = loader.load();

        SecondaryController controller = loader.getController();
        controller.setTenis(tenis);

        Stage stage = (Stage) marcaTenis.getScene().getWindow();
        stage.setScene(new Scene(root));
    }
    // FIM TENIS

    // PEIXES
    @FXML
    private TextField nomePeixe;
    @FXML
    private TextField tipoPeixe;
    @FXML
    private TextField quantidadePeixe;
    @FXML
    private Button salvarPeixe;

    public void adicionarPeixe() throws IOException {
        String nome = nomePeixe.getText();
        String tipo = tipoPeixe.getText();
        int quantidade = Integer.parseInt(quantidadePeixe.getText());

        Peixes peixe = new Peixes(nome, tipo, quantidade);

        FXMLLoader loader = new FXMLLoader(getClass().getResource("secondary.fxml"));
        Parent root = loader.load();

        SecondaryController controller = loader.getController();
        controller.setPeixe(peixe);

        Stage stage = (Stage) nomePeixe.getScene().getWindow();
        stage.setScene(new Scene(root));
    }
    // FIM PEIXES

    // CACHORROS
    @FXML
    private TextField nomeCachorro;
    @FXML
    private TextField racaCachorro;
    @FXML
    private TextField idadeCachorro;
    @FXML
    private Button salvarCachorro;

    public void adicionarCachorro() throws IOException {
        String nome = nomeCachorro.getText();
        String raca = racaCachorro.getText();
        int idade = Integer.parseInt(idadeCachorro.getText());

        Cachorro cachorro = new Cachorro(nome, raca, idade);

        FXMLLoader loader = new FXMLLoader(getClass().getResource("secondary.fxml"));
        Parent root = loader.load();

        SecondaryController controller = loader.getController();
        controller.setCachorro(cachorro);

        Stage stage = (Stage) nomeCachorro.getScene().getWindow();
        stage.setScene(new Scene(root));
    }
    // FIM CACHORROS

    // FLORES
    @FXML
    private TextField nomeFlor;
    @FXML
    private TextField corFlor;
    @FXML
    private TextField tamanhoFlor;
    @FXML
    private Button salvarFlor;

    public void adicionarFlor() throws IOException {
        String nome = nomeFlor.getText();
        String cor = corFlor.getText();
        int tamanho = Integer.parseInt(tamanhoFlor.getText());

        Flores flor = new Flores(nome, cor, tamanho);

        FXMLLoader loader = new FXMLLoader(getClass().getResource("secondary.fxml"));
        Parent root = loader.load();

        SecondaryController controller = loader.getController();
        controller.setFlor(flor);

        Stage stage = (Stage) nomeFlor.getScene().getWindow();
        stage.setScene(new Scene(root));
    }
    // FIM FLORES

    // MANGÁS
    @FXML
    private TextField nomeManga;
    @FXML
    private TextField capituloManga;
    @FXML
    private TextField notaManga;
    @FXML
    private Button salvarManga;

    public void adicionarManga() throws IOException {
        String nome = nomeManga.getText();
        int capitulo = Integer.parseInt(capituloManga.getText());
        int nota = Integer.parseInt(notaManga.getText());

        Mangas manga = new Mangas(nome, capitulo, nota);

        FXMLLoader loader = new FXMLLoader(getClass().getResource("secondary.fxml"));
        Parent root = loader.load();

        SecondaryController controller = loader.getController();
        controller.setManga(manga);

        Stage stage = (Stage) nomeManga.getScene().getWindow();
        stage.setScene(new Scene(root));
    }
    // FIM MANGÁS
}
