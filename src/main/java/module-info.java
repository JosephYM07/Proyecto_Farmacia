module farmacia.proyecto_farmacia {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.security.jgss;
    requires java.sql;

    opens Proyecto_farmacia to javafx.fxml;
    exports Proyecto_farmacia;
    exports Proyecto_farmacia.Controladores;
    opens Proyecto_farmacia.Controladores to javafx.fxml;

    // Abre el paquete Proyecto_farmacia.Controladores.Modulos para javafx.base
    opens Proyecto_farmacia.Controladores.Modulos to javafx.base;
}
