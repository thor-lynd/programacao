package org.example;

import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.util.List;

public class TenisController {

    @FXML
    private TextField marcaTenis;

    @FXML
    private TextField corTenis;

    @FXML
    private TextField tamanhoTenis;

    @FXML
    private TextArea areaTextoTenis;

    private TenisDAO dao = new TenisDAO();
    private List<Tenis> listaTenis;

    @FXML
    public void initialize() {
        carregarLista();
    }

    private void carregarLista() {
        listaTenis = dao.listarTodos();
        atualizarAreaTexto();
    }

    @FXML
    private void adicionarTenis() {
        String marca = marcaTenis.getText().trim();
        String cor = corTenis.getText().trim();
        String tamanhoStr = tamanhoTenis.getText().trim();

        if (marca.isEmpty() || cor.isEmpty() || tamanhoStr.isEmpty()) {
            mostrarAlerta("Erro", "Preencha todos os campos!");
            return;
        }

        int tamanho;
        try {
            tamanho = Integer.parseInt(tamanhoStr);
        } catch (NumberFormatException e) {
            mostrarAlerta("Erro", "Tamanho deve ser um número inteiro.");
            return;
        }

        Tenis tenis = new Tenis(marca, cor, tamanho);
        dao.inserir(tenis);
        limparCampos();
        carregarLista();
    }

    @FXML
    private void editarTenis() {
        String marca = marcaTenis.getText().trim();
        String cor = corTenis.getText().trim();
        String tamanhoStr = tamanhoTenis.getText().trim();

        if (marca.isEmpty() || cor.isEmpty() || tamanhoStr.isEmpty()) {
            mostrarAlerta("Erro", "Preencha todos os campos para editar!");
            return;
        }

        int tamanho;
        try {
            tamanho = Integer.parseInt(tamanhoStr);
        } catch (NumberFormatException e) {
            mostrarAlerta("Erro", "Tamanho deve ser um número inteiro.");
            return;
        }

        // Encontra o tênis para editar pela marca (simplificado)
        Tenis tenisParaEditar = null;
        for (Tenis t : listaTenis) {
            if (t.getMarca().equalsIgnoreCase(marca)) {
                tenisParaEditar = t;
                break;
            }
        }

        if (tenisParaEditar != null) {
            tenisParaEditar.setCor(cor);
            tenisParaEditar.setTamanho(tamanho);
            dao.atualizar(tenisParaEditar);
            limparCampos();
            carregarLista();
        } else {
            mostrarAlerta("Erro", "Tênis com a marca informada não encontrado.");
        }
    }

    @FXML
    private void voltarMenu() {
        // Implementar navegação ou fechar janela
        System.out.println("Voltar ao menu");
    }

    private void limparCampos() {
        marcaTenis.clear();
        corTenis.clear();
        tamanhoTenis.clear();
    }

    private void atualizarAreaTexto() {
        StringBuilder sb = new StringBuilder();
        for (Tenis t : listaTenis) {
            sb.append("Marca: ").append(t.getMarca())
                    .append(", Cor: ").append(t.getCor())
                    .append(", Tamanho: ").append(t.getTamanho())
                    .append("\n");
        }
        areaTextoTenis.setText(sb.toString());
    }

    private void mostrarAlerta(String titulo, String mensagem) {
        Alert alerta = new Alert(Alert.AlertType.ERROR);
        alerta.setTitle(titulo);
        alerta.setHeaderText(null);
        alerta.setContentText(mensagem);
        alerta.showAndWait();
    }
}
