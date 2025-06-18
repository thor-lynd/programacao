package org.example;

import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.sql.SQLException;
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

    private FlorDAO dao = new FlorDAO();

    private Flor ultimaFlorInserida = null;

    @FXML
    private void adicionarFlor() {
        try {
            String nome = nomeFlor.getText().trim();
            String cor = corFlor.getText().trim();
            int altura = Integer.parseInt(alturaFlor.getText().trim());

            if (nome.isEmpty() || cor.isEmpty()) {
                areaTextoFlores.setText("Nome e Cor não podem ser vazios.");
                return;
            }

            Flor flor = new Flor(nome, cor, altura);
            dao.inserir(flor);
            ultimaFlorInserida = flor;

            areaTextoFlores.setText("Flor adicionada com sucesso:\n" + montarInfoFlor(flor));
            limparCampos();

        } catch (NumberFormatException e) {
            areaTextoFlores.setText("Erro: Altura inválida.");
        } catch (SQLException e) {
            areaTextoFlores.setText("Erro ao inserir no banco: " + e.getMessage());
        }
    }

    @FXML
    private void visualizarFlores() {
        try {
            List<Flor> lista = dao.listarTodos();
            if (lista.isEmpty()) {
                areaTextoFlores.setText("Nenhuma flor cadastrada.");
                return;
            }

            StringBuilder sb = new StringBuilder("Flores cadastradas:\n\n");
            for (Flor f : lista) {
                sb.append(montarInfoFlor(f)).append("\n\n");
            }
            areaTextoFlores.setText(sb.toString());

        } catch (SQLException e) {
            areaTextoFlores.setText("Erro ao buscar no banco: " + e.getMessage());
        }
    }

    @FXML
    private void editarUltimaFlor() {
        if (ultimaFlorInserida == null) {
            areaTextoFlores.setText("Nenhuma flor para editar. Adicione uma primeiro.");
            return;
        }

        try {
            String novoNome = nomeFlor.getText().trim();
            String novaCor = corFlor.getText().trim();
            int novaAltura = Integer.parseInt(alturaFlor.getText().trim());

            if (novoNome.isEmpty() || novaCor.isEmpty()) {
                areaTextoFlores.setText("Nome e Cor não podem ser vazios.");
                return;
            }

            Flor florAtualizada = new Flor(novoNome, novaCor, novaAltura);

            // Atualiza no banco usando o nome antigo como chave
            dao.atualizar(florAtualizada, ultimaFlorInserida.getNomeFlor());

            areaTextoFlores.setText("Última flor atualizada com sucesso:\n" + montarInfoFlor(florAtualizada));

            ultimaFlorInserida = florAtualizada;
            limparCampos();

        } catch (NumberFormatException e) {
            areaTextoFlores.setText("Erro: Altura inválida.");
        } catch (SQLException e) {
            areaTextoFlores.setText("Erro ao atualizar no banco: " + e.getMessage());
        }
    }

    @FXML
    private void voltarMenu() {
        try {
            App.setRoot("Menu");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private String montarInfoFlor(Flor flor) {
        return "Nome: " + flor.getNomeFlor() +
                "\nCor: " + flor.getCorFlor() +
                "\nAltura: " + flor.getAlturaFlor() + " cm";
    }

    private void limparCampos() {
        nomeFlor.clear();
        corFlor.clear();
        alturaFlor.clear();
    }
}
