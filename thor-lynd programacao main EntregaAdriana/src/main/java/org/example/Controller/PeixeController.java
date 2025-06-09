package org.example;

import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.util.ArrayList;
import java.util.List;

public class PeixeController {

    @FXML private TextField nomePeixe;
    @FXML private TextField tipoPeixe;
    @FXML private TextField quantidadePeixe;
    @FXML private TextArea areaTextoPeixe;

    private final List<Peixe> listaPeixes = new ArrayList<>();

    @FXML
    public void adicionarPeixe() {
        try {
            String nome = nomePeixe.getText();
            String tipo = tipoPeixe.getText();
            int quantidade = Integer.parseInt(quantidadePeixe.getText());

            Peixe peixe = new Peixe(nome, tipo, quantidade);
            listaPeixes.add(peixe);

            limparCampos();
            areaTextoPeixe.setText("Peixe adicionado com sucesso!");
        } catch (NumberFormatException e) {
            areaTextoPeixe.setText("Erro: quantidade deve ser um número inteiro.");
        }
    }

    @FXML
    public void visualizarPeixes() {
        if (listaPeixes.isEmpty()) {
            areaTextoPeixe.setText("Nenhum peixe cadastrado.");
            return;
        }

        StringBuilder sb = new StringBuilder();
        for (Peixe peixe : listaPeixes) {
            sb.append("Nome: ").append(peixe.getNomePeixe()).append("\n")
                    .append("Tipo: ").append(peixe.getTipoPeixe()).append("\n")
                    .append("Quantidade: ").append(peixe.getQuantidadePeixe()).append("\n")
                    .append("-------------------------\n");
        }
        areaTextoPeixe.setText(sb.toString());
    }

    private void limparCampos() {
        nomePeixe.clear();
        tipoPeixe.clear();
        quantidadePeixe.clear();
    }
}
