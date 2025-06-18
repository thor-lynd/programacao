package org.example;

import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.util.ArrayList;
import java.util.List;

public class FlorController {

    @FXML private TextField nomeFlor;
    @FXML private TextField corFlor;
    @FXML private TextField alturaFlor;
    @FXML private TextArea areaTextoFlor;

    private final List<Flor> listaFlores = new ArrayList<>();

    @FXML
    public void adicionarFlor() {
        try {
            String nome = nomeFlor.getText();
            String cor = corFlor.getText();
            int altura = Integer.parseInt(alturaFlor.getText());

            Flor flor = new Flor(nome, cor, altura);
            listaFlores.add(flor);

            limparCampos();
            areaTextoFlor.setText("Flor adicionada com sucesso!");
        } catch (NumberFormatException e) {
            areaTextoFlor.setText("Erro: altura deve ser um número inteiro.");
        }
    }

    @FXML
    public void visualizarFlores() {
        if (listaFlores.isEmpty()) {
            areaTextoFlor.setText("Nenhuma flor cadastrada.");
            return;
        }

        StringBuilder sb = new StringBuilder();
        for (Flor flor : listaFlores) {
            sb.append("Nome: ").append(flor.getNomeFlor()).append("\n")
                    .append("Cor: ").append(flor.getCorFlor()).append("\n")
                    .append("Altura: ").append(flor.getAlturaFlor()).append(" cm\n")
                    .append("-------------------------\n");
        }
        areaTextoFlor.setText(sb.toString());
    }

    private void limparCampos() {
        nomeFlor.clear();
        corFlor.clear();
        alturaFlor.clear();
    }
}
