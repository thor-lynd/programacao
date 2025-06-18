package org.example;

import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.util.ArrayList;
import java.util.List;

public class PessoaController {

    @FXML private TextField nomePessoa;
    @FXML private TextField idadePessoa;
    @FXML private TextField ocupacaoPessoa;
    @FXML private TextArea areaTextoPessoa;

    private final List<Pessoa> listaPessoas = new ArrayList<>();

    @FXML
    public void adicionarPessoa() {
        try {
            String nome = nomePessoa.getText();
            int idade = Integer.parseInt(idadePessoa.getText());
            String ocupacao = ocupacaoPessoa.getText();

            Pessoa pessoa = new Pessoa(nome, idade, ocupacao);
            listaPessoas.add(pessoa);

            limparCampos();
            areaTextoPessoa.setText("Pessoa adicionada com sucesso!");
        } catch (NumberFormatException e) {
            areaTextoPessoa.setText("Erro: idade deve ser um número inteiro.");
        }
    }

    @FXML
    public void visualizarPessoas() {
        if (listaPessoas.isEmpty()) {
            areaTextoPessoa.setText("Nenhuma pessoa cadastrada.");
            return;
        }

        StringBuilder sb = new StringBuilder();
        for (Pessoa p : listaPessoas) {
            sb.append("Nome: ").append(p.getNomePessoa()).append("\n")
                    .append("Idade: ").append(p.getIdadePessoa()).append("\n")
                    .append("Ocupação: ").append(p.getOcupacaoPessoa()).append("\n")
                    .append("-------------------------\n");
        }
        areaTextoPessoa.setText(sb.toString());
    }

    private void limparCampos() {
        nomePessoa.clear();
        idadePessoa.clear();
        ocupacaoPessoa.clear();
    }
}
