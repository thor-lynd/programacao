package org.example;

import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.util.ArrayList;
import java.util.List;

public class TenisController {

    @FXML private TextField marcaTenis;
    @FXML private TextField corTenis;
    @FXML private TextField tamanhoTenis;
    @FXML private TextArea areaTextoTenis;

    private final List<Tenis> listaTenis = new ArrayList<>();

    @FXML
    public void adicionarTenis() {
        try {
            String marca = marcaTenis.getText();
            String cor = corTenis.getText();
            int tamanho = Integer.parseInt(tamanhoTenis.getText());

            Tenis tenis = new Tenis(marca, cor, tamanho);
            listaTenis.add(tenis);

            limparCampos();
            areaTextoTenis.setText("Tênis adicionado com sucesso!");
        } catch (NumberFormatException e) {
            areaTextoTenis.setText("Erro: tamanho deve ser um número inteiro.");
        }
    }

    @FXML
    public void visualizarTenis() {
        if (listaTenis.isEmpty()) {
            areaTextoTenis.setText("Nenhum tênis cadastrado.");
            return;
        }

        StringBuilder sb = new StringBuilder();
        for (Tenis t : listaTenis) {
            sb.append("Marca: ").append(t.getMarcaTenis()).append("\n")
                    .append("Cor: ").append(t.getCorTenis()).append("\n")
                    .append("Tamanho: ").append(t.getTamanhoTenis()).append("\n")
                    .append("-------------------------\n");
        }
        areaTextoTenis.setText(sb.toString());
    }

    private void limparCampos() {
        marcaTenis.clear();
        corTenis.clear();
        tamanhoTenis.clear();
    }
}
