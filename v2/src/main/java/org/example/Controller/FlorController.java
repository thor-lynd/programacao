package org.example;

import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class FlorController {
    @FXML
    private TextField nomeFlor;
    @FXML
    private TextField corFlor;
    @FXML
    private TextField alturaFlor;
    @FXML
    private TextArea areaTextoFlores;

    private final List<Flor> flores = new ArrayList<>();

    @FXML
    private void adicionarFlor() {
        try {
            String nome = nomeFlor.getText();
            String cor = corFlor.getText();
            int altura = Integer.parseInt(alturaFlor.getText());

            Flor flor = new Flor(nome, cor, altura);
            flores.add(flor);

            nomeFlor.clear();
            corFlor.clear();
            alturaFlor.clear();

            areaTextoFlores.setText("Flor adicionada com sucesso!");

        } catch (NumberFormatException e) {
            areaTextoFlores.setText("Altura inválida.");
        }
    }

    @FXML
    private void visualizarFlores() {
        StringBuilder sb = new StringBuilder();
        for (Flor f : flores) {
            sb.append("Nome: ").append(f.getNomeFlor()).append("\n")
                    .append("Cor: ").append(f.getCorFlor()).append("\n")
                    .append("Altura: ").append(f.getAlturaFlor()).append(" cm\n")
                    .append("---\n");
        }
        areaTextoFlores.setText(sb.toString());
    }

    @FXML
    private void editarUltimaFlor() {
        if (flores.isEmpty()) {
            areaTextoFlores.setText("Nenhuma flor cadastrada para editar.");
            return;
        }

        String novoNome = nomeFlor.getText();
        String novaCor = corFlor.getText();
        String novaAlturaStr = alturaFlor.getText();

        if (novoNome.isEmpty() || novaCor.isEmpty() || novaAlturaStr.isEmpty()) {
            areaTextoFlores.setText("Preencha todos os campos para editar a flor.");
            return;
        }

        try {
            int novaAltura = Integer.parseInt(novaAlturaStr);

            Flor ultimaFlor = flores.get(flores.size() - 1);
            ultimaFlor.setNomeFlor(novoNome);
            ultimaFlor.setCorFlor(novaCor);
            ultimaFlor.setAlturaFlor(novaAltura);

            areaTextoFlores.setText("Última flor editada com sucesso:\n"
                    + "Nome: " + ultimaFlor.getNomeFlor() + "\n"
                    + "Cor: " + ultimaFlor.getCorFlor() + "\n"
                    + "Altura: " + ultimaFlor.getAlturaFlor() + " cm");

            // Limpa os campos após edição
            nomeFlor.clear();
            corFlor.clear();
            alturaFlor.clear();

        } catch (NumberFormatException e) {
            areaTextoFlores.setText("Altura inválida. Use apenas números inteiros.");
        }
    }

    @FXML
    private void voltarMenu() throws IOException {
        App.setRoot("menu");
    }
}
