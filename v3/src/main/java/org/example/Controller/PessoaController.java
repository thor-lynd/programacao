package org.example;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.sql.SQLException;

public class PessoaController {

    @FXML
    private TextField nomePessoa;

    @FXML
    private TextField idadePessoa;

    @FXML
    private TextField ocupacaoPessoa;

    @FXML
    private TextArea areaTextoPessoas;

    @FXML
    private ImageView imagemPessoa;

    private PessoaDAO pessoaDAO = new PessoaDAO();
    private String nomePessoaAntigo = null;

    @FXML
    public void adicionarPessoa() {
        try {
            String nome = nomePessoa.getText();
            int idade = Integer.parseInt(idadePessoa.getText());
            String ocupacao = ocupacaoPessoa.getText();

            Pessoa pessoa = new Pessoa(nome, idade, ocupacao);
            pessoaDAO.inserir(pessoa);
            areaTextoPessoas.setText("Pessoa adicionada:\n" + formatarPessoa(pessoa));
            nomePessoaAntigo = nome;
            carregarImagem(nome);
            limparCampos();
        } catch (NumberFormatException e) {
            areaTextoPessoas.setText("Erro: Idade deve ser número inteiro.");
        } catch (SQLException e) {
            areaTextoPessoas.setText("Erro ao adicionar pessoa: " + e.getMessage());
        }
    }

    @FXML
    public void editarPessoa() {
        if (nomePessoaAntigo == null) {
            areaTextoPessoas.setText("Nenhuma pessoa selecionada para edição.");
            return;
        }

        try {
            String nome = nomePessoa.getText();
            int idade = Integer.parseInt(idadePessoa.getText());
            String ocupacao = ocupacaoPessoa.getText();

            Pessoa pessoa = new Pessoa(nome, idade, ocupacao);
            pessoaDAO.atualizar(pessoa, nomePessoaAntigo);
            areaTextoPessoas.setText("Pessoa atualizada:\n" + formatarPessoa(pessoa));
            nomePessoaAntigo = nome;
            carregarImagem(nome);
            limparCampos();
        } catch (NumberFormatException e) {
            areaTextoPessoas.setText("Erro: Idade deve ser número inteiro.");
        } catch (SQLException e) {
            areaTextoPessoas.setText("Erro ao atualizar pessoa: " + e.getMessage());
        }
    }

    @FXML
    public void voltarMenu() {
        // Implemente lógica para voltar ao menu principal
    }

    @FXML
    public void initialize() {
        areaTextoPessoas.setEditable(false);
        imagemPessoa.setImage(new Image("file:resources/default_pessoa.png"));
    }

    private String formatarPessoa(Pessoa pessoa) {
        return "Nome: " + pessoa.getNomePessoa() +
                "\nIdade: " + pessoa.getIdadePessoa() +
                "\nOcupação: " + pessoa.getOcupacaoPessoa();
    }

    private void limparCampos() {
        nomePessoa.clear();
        idadePessoa.clear();
        ocupacaoPessoa.clear();
    }

    private void carregarImagem(String nome) {
        try {
            String caminhoImagem = "file:resources/" + nome.toLowerCase().replaceAll(" ", "_") + ".png";
            Image imagem = new Image(caminhoImagem);
            imagemPessoa.setImage(imagem);
        } catch (Exception e) {
            imagemPessoa.setImage(new Image("file:resources/default_pessoa.png"));
        }
    }
}
