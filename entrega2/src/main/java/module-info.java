module org.example.entrega2 {
    requires javafx.controls;
    requires javafx.fxml;

    requires com.dlsc.formsfx;

    opens org.example.entrega2 to javafx.fxml;
    exports org.example.entrega2;
}