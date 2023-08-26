module farmacia.proyecto_farmacia {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.security.jgss;


    opens farmacia.proyecto_farmacia to javafx.fxml;
    exports farmacia.proyecto_farmacia;
}