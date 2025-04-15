package org.example;

import javafx.fxml.FXML;
import javafx.scene.control.TextArea;

public class SecondaryController {

    @FXML
    private TextArea infoGeral;

    private Celulares celular;

    public void setCelular(Celulares celular) {
        this.celular = celular;
        mostrarInformacoesCel();
    }

    private void mostrarInformacoesCel() {
        celular.mostrarInfo();
        String info = "Informações do Celular:\n" +
                "Marca: " + celular.celMarca + "\n" +
                "Modelo: " + celular.celModelo + "\n" +
                "Preço: R$" + celular.celPreco + "\n" +
                "Ação: " + "Ligando...\n" + celular.ligar() + "\n" +
                "Desligando...\n" + celular.desligar();
        infoGeral.setText(info);  // Atualiza a TextArea
    }

    private Albums album;

    public void setAlbum(Albums album) {
        this.album = album;
        mostrarInformacoesAlbum();
    }

    private void mostrarInformacoesAlbum() {
        album.escutar();
        String info = "Informações do Álbum:\n" +
                "Nome: " + album.getNome() + "\n" +
                "Músicas: " + album.getMusicas() + "\n" +
                "Artista: " + album.getArtista() + "\n" +
                "Ações: \n" +
                "- Escutando o album: " + album.getNome() + " Do/Da artista: " + album.getArtista() + "\n" +
                "- Pulando a musica: " + album.getMusicas() + "\n" +
                "- Acabando o album.";
        infoGeral.setText(info);
    }

    private Carros carro;

    public void setCarro(Carros carro) {
        this.carro = carro;
        mostrarInformacoesCarro();
    }

    private void mostrarInformacoesCarro() {
        carro.ligar();
        String info = "Informações do Carro:\n" +
                "Marca: " + carro.getMarcaCarro() + "\n" +
                "Modelo: " + carro.getModeloCarro() + "\n" +
                "Ano: " + carro.getAnoCarro() + "\n" +
                "Ação: Dirigindo o carro: " + carro.getMarcaCarro() + " " + carro.getModeloCarro() + " do ano : " + carro.getAnoCarro();
        infoGeral.setText(info);
    }

    private Tenis tenis;

    public void setTenis(Tenis tenis) {
        this.tenis = tenis;
        mostrarInformacoesTenis();
    }

    private void mostrarInformacoesTenis() {
        tenis.exibirDetalhes();
        String info = "Informações do Tênis:\n" +
                "Marca: " + tenis.getMarcaTenis() + "\n" +
                "Cor: " + tenis.getCorTenis() + "\n" +
                "Tamanho: " + tenis.getTamanhoTenis() + "\n" +
                "Ação: Calçando o tênis da marca: " + tenis.getMarcaTenis() + " Da cor: " + tenis.getCorTenis() + " que tem : " + tenis.getTamanhoTenis() + " Cm";
        infoGeral.setText(info);
    }

    private Pessoas pessoa;

    public void setPessoa(Pessoas pessoa) {
        this.pessoa = pessoa;
        mostrarInformacoesPessoa();
    }

    private void mostrarInformacoesPessoa() {
        String info = "Informações da Pessoa:\n" +
                "Nome: " + pessoa.getNomePessoa() + "\n" +
                "Idade: " + pessoa.getIdadePessoa() + "\n" +
                "Ocupação: " + pessoa.getOcupacaoPessoa() + "\n" +
                "Ação: " + pessoa.getNomePessoa() + " está falando sua idade, que é: " + pessoa.getIdadePessoa() + "e que trabalha como : " + pessoa.getOcupacaoPessoa();
        infoGeral.setText(info);
    }

    private Peixes peixe;

    public void setPeixe(Peixes peixe) {
        this.peixe = peixe;
        mostrarInformacoesPeixe();
    }

    private void mostrarInformacoesPeixe() {
        peixe.nomear();
        String info = "Informações do Peixe:\n" +
                "Nome: " + peixe.getNomePeixe() + "\n" +
                "Tipo: " + peixe.getTipoPeixe() + "\n" +
                "Quantidade: " + peixe.getQuantidadePeixe() + "\n" +
                "Ação: Comendo o peixe: " + peixe.getNomePeixe() + " Que tem : " + peixe.getQuantidadePeixe() + " Unidades" + " Do tipo: " + peixe.getTipoPeixe();
        infoGeral.setText(info);
    }

    private Cachorro cachorro;

    public void setCachorro(Cachorro cachorro) {
        this.cachorro = cachorro;
        mostrarInformacoesCachorro();
    }

    private void mostrarInformacoesCachorro() {
        cachorro.latir();
        String info = "Informações do Cachorro:\n" +
                "Nome: " + cachorro.getNomeCachorro() + "\n" +
                "Raça: " + cachorro.getRacaCachorro() + "\n" +
                "Idade: " + cachorro.getIdadeCachorro() + "\n" +
                "Ação: Brincando com o: "  + cachorro.getNomeCachorro() + " Que tem : " + cachorro.getIdadeCachorro() + " Anos" + " Da raça: " + cachorro.getRacaCachorro();
        infoGeral.setText(info);
    }

    private Flores flor;

    public void setFlor(Flores flor) {
        this.flor = flor;
        mostrarInformacoesFlor();
    }

    private void mostrarInformacoesFlor() {
        flor.florescer();
        String info = "Informações da Flor:\n" +
                "Nome: " + flor.getNomeFlor() + "\n" +
                "Cor: " + flor.getCorFlor() + "\n" +
                "Tamanho: " + flor.getAlturaFlor() + "\n" +
                "Ação: Regando a flor: " + flor.getNomeFlor() + " Da cor:" + flor.getCorFlor() + " Que tem : " + flor.getAlturaFlor() + " Cm";
        infoGeral.setText(info);
    }

    private Mangas manga;

    public void setManga(Mangas manga) {
        this.manga = manga;
        mostrarInformacoesManga();
    }

    private void mostrarInformacoesManga() {
        manga.falarCapitulo();
        String info = "Informações do Mangá:\n" +
                "Nome: " + manga.getNomeManga() + "\n" +
                "Capítulo: " + manga.getCapituloManga() + "\n" +
                "Nota: " + manga.getNotaManga() + "\n" +
                "Ação: Lendo o mangá: " + manga.getNomeManga() + "Você está no capitulo :" + manga.getCapituloManga() + " A nota que você deu enquanto isso foi :" + manga.getNotaManga();
        infoGeral.setText(info);
    }
}
