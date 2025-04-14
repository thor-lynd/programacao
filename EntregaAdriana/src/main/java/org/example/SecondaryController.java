package org.example;

import javafx.fxml.FXML;
import javafx.scene.control.TextArea;

public class SecondaryController {

    @FXML
    private TextArea infoCelular;

    private Celulares celular;

    public void setCelular(Celulares celular) {
        this.celular = celular;
        mostrarInformacoes();
    }

    private void mostrarInformacoes() {
        celular.mostrarInfo();
        String info = "Informações do Celular:\n" +
                "Marca: " + celular.celMarca + "\n" +
                "Modelo: " + celular.celModelo + "\n" +
                "Preço: R$" + celular.celPreco + "\n" +
                "Ação: " + "Ligando...\n" + celular.ligar() + "\n" +
                "Desligando...\n" + celular.desligar();
        infoCelular.setText(info);  // Atualiza a TextArea
    }
}

