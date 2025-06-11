package org.example;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.sql.SQLException;
import java.util.List;

public class PeixeController {

    @FXML
    private TextField nomePeixe;

    @FXML
    private TextField tipoPeixe;

    @FXML
    private TextField quantidadePeixe;

    @FXML
    private TextArea areaTextoPeixes;

    @FXML
    private ImageView imagemPeixe;

    private PeixeDAO peixeDAO = new PeixeDAO();
    private String nomePeixeAntigo = null;

    @FXML
    public void adicionarPeixe() {
        try {
            String nome = nomePeixe.getText();
            String tipo = tipoPeixe.getText();
            int quantidade = Integer.parseInt(quantidadePeixe.getText());

            Peixe peixe = new Peixe(nome, tipo, quantidade);
            peixeDAO.inserir(peixe);
            areaTextoPeixes.setText("Peixe adicionado:\n" + formatarPeixe(peixe));
            nomePeixeAntigo = nome;
            carregarImagem(nome);
            limparCampos();
        } catch (NumberFormatException e) {
            areaTextoPeixes.setText("Erro: Quantidade deve ser número inteiro.");
        } catch (SQLException e) {
            areaTextoPeixes.setText("Erro ao adicionar peixe: " + e.getMessage());
        }
    }

    @FXML
    public void editarPeixe() {
        if (nomePeixeAntigo == null) {
            areaTextoPeixes.setText("Nenhum peixe selecionado para edição.");
            return;
        }

        try {
            String nome = nomePeixe.getText();
            String tipo = tipoPeixe.getText();
            int quantidade = Integer.parseInt(quantidadePeixe.getText());

            Peixe peixe = new Peixe(nome, tipo, quantidade);
            peixeDAO.atualizar(peixe, nomePeixeAntigo);
            areaTextoPeixes.setText("Peixe atualizado:\n" + formatarPeixe(peixe));
            nomePeixeAntigo = nome;
            carregarImagem(nome);
            limparCampos();
        } catch (NumberFormatException e) {
            areaTextoPeixes.setText("Erro: Quantidade deve ser número inteiro.");
        } catch (SQLException e) {
            areaTextoPeixes.setText("Erro ao atualizar peixe: " + e.getMessage());
        }
    }

    @FXML
    public void voltarMenu() {
        // Implemente lógica para voltar ao menu principal
    }

    @FXML
    public void initialize() {
        areaTextoPeixes.setEditable(false);
        imagemPeixe.setImage(new Image("file:resources/default_peixe.png"));
    }

    private String formatarPeixe(Peixe peixe) {
        return "Nome: " + peixe.getNomePeixe() +
                "\nTipo: " + peixe.getTipoPeixe() +
                "\nQuantidade: " + peixe.getQuantidadePeixe();
    }

    private void limparCampos() {
        nomePeixe.clear();
        tipoPeixe.clear();
        quantidadePeixe.clear();
    }

    private void carregarImagem(String nome) {
        try {
            String caminhoImagem = "file:resources/" + nome.toLowerCase().replaceAll(" ", "_") + ".png";
            Image imagem = new Image(caminhoImagem);
            imagemPeixe.setImage(imagem);
        } catch (Exception e) {
            imagemPeixe.setImage(new Image("file:resources/default_peixe.png"));
        }
    }
}
