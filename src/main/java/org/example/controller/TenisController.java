package org.example.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import org.example.Classes.Tenis;
import org.example.dao.TenisDAO;

import java.util.Optional;

public class TenisController {

    @FXML
    private ListView<Tenis> listViewTenis;

    @FXML
    private ImageView imageViewTenis;

    private final TenisDAO tenisDAO = new TenisDAO();
    private final ObservableList<Tenis> tenisList = FXCollections.observableArrayList();

    @FXML
    public void initialize() {
        listViewTenis.setItems(tenisList);
        atualizarLista();
    }

    @FXML
    private void handleAdicionar() {
        Tenis novo = exibirDialogo(null);
        if (novo != null) {
            tenisDAO.adicionar(novo);
            atualizarLista();
        }
    }

    @FXML
    private void handleEditar() {
        Tenis selecionado = listViewTenis.getSelectionModel().getSelectedItem();
        if (selecionado != null) {
            Tenis editado = exibirDialogo(selecionado);
            if (editado != null) {
                tenisDAO.atualizar(editado);
                atualizarLista();
            }
        } else {
            mostrarAlerta("Selecione um tênis para editar.");
        }
    }

    @FXML
    private void handleApagar() {
        Tenis selecionado = listViewTenis.getSelectionModel().getSelectedItem();
        if (selecionado != null) {
            tenisDAO.deletar(selecionado.getId());
            atualizarLista();
        } else {
            mostrarAlerta("Selecione um tênis para apagar.");
        }
    }

    @FXML
    private void handleAtualizar() {
        atualizarLista();
    }

    @FXML
    private void handleExibirDetalhes() {
        Tenis t = listViewTenis.getSelectionModel().getSelectedItem();
        if (t != null) {
            t.exibirDetalhes();
            mostrarMensagem("Detalhes do Tênis",
                    "Marca: " + t.getMarca() + "\nCor: " + t.getCor() + "\nTamanho: " + t.getTamanho());
        } else {
            mostrarAlerta("Selecione um tênis para exibir detalhes.");
        }
    }

    @FXML
    private void handleMudarTamanho() {
        Tenis t = listViewTenis.getSelectionModel().getSelectedItem();
        if (t != null) {
            TextInputDialog dialog = new TextInputDialog(Integer.toString(t.getTamanho()));
            dialog.setTitle("Mudar Tamanho");
            dialog.setHeaderText("Digite o novo tamanho:");
            Optional<String> result = dialog.showAndWait();
            result.ifPresent(tamanhoStr -> {
                try {
                    int novoTamanho = Integer.parseInt(tamanhoStr);
                    t.mudarTamanho(novoTamanho);
                    tenisDAO.atualizar(t);
                    atualizarLista();
                } catch (NumberFormatException e) {
                    mostrarAlerta("Tamanho inválido.");
                }
            });
        } else {
            mostrarAlerta("Selecione um tênis para mudar o tamanho.");
        }
    }

    @FXML
    private void handleCompararTamanho() {
        Tenis t1 = listViewTenis.getSelectionModel().getSelectedItem();
        if (t1 != null) {
            ChoiceDialog<Tenis> dialog = new ChoiceDialog<>(null, tenisList);
            dialog.setTitle("Comparar Tamanho");
            dialog.setHeaderText("Selecione outro tênis para comparar tamanho:");
            Optional<Tenis> result = dialog.showAndWait();
            result.ifPresent(t2 -> {
                boolean iguais = t1.compararTamanho(t2);
                String mensagem = iguais ? "Os tamanhos são iguais." : "Os tamanhos são diferentes.";
                mostrarMensagem("Comparação de Tamanho", mensagem);
            });
        } else {
            mostrarAlerta("Selecione um tênis para comparar o tamanho.");
        }
    }

    private void atualizarLista() {
        tenisList.setAll(tenisDAO.listarTodos());
    }

    private Tenis exibirDialogo(Tenis tenisExistente) {
        Dialog<Tenis> dialog = new Dialog<>();
        dialog.setTitle(tenisExistente == null ? "Adicionar Tênis" : "Editar Tênis");

        TextField marcaField = new TextField();
        TextField corField = new TextField();
        TextField tamanhoField = new TextField();

        if (tenisExistente != null) {
            marcaField.setText(tenisExistente.getMarca());
            corField.setText(tenisExistente.getCor());
            tamanhoField.setText(String.valueOf(tenisExistente.getTamanho()));
        }

        VBox vbox = new VBox(10,
                new Label("Marca:"), marcaField,
                new Label("Cor:"), corField,
                new Label("Tamanho:"), tamanhoField);

        dialog.getDialogPane().setContent(vbox);

        ButtonType salvarBtn = new ButtonType("Salvar", ButtonBar.ButtonData.OK_DONE);
        dialog.getDialogPane().getButtonTypes().addAll(salvarBtn, ButtonType.CANCEL);

        dialog.setResultConverter(buttonType -> {
            if (buttonType == salvarBtn) {
                try {
                    String marca = marcaField.getText();
                    String cor = corField.getText();
                    int tamanho = Integer.parseInt(tamanhoField.getText());

                    if (tenisExistente != null) {
                        tenisExistente.setMarca(marca);
                        tenisExistente.setCor(cor);
                        tenisExistente.setTamanho(tamanho);
                        return tenisExistente;
                    } else {
                        return new Tenis(0, marca, cor, tamanho);
                    }
                } catch (NumberFormatException e) {
                    mostrarAlerta("Tamanho inválido.");
                }
            }
            return null;
        });

        Optional<Tenis> result = dialog.showAndWait();
        return result.orElse(null);
    }

    private void mostrarAlerta(String mensagem) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Informação");
        alert.setHeaderText(null);
        alert.setContentText(mensagem);
        alert.showAndWait();
    }

    private void mostrarMensagem(String titulo, String mensagem) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensagem);
        alert.showAndWait();
    }
}
