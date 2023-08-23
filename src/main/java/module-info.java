module farmacia.proyecto_farmacia {
    requires javafx.controls;
    requires javafx.fxml;


    opens farmacia.proyecto_farmacia to javafx.fxml;
    exports farmacia.proyecto_farmacia;
}