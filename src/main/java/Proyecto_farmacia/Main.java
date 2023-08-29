package Proyecto_farmacia;

import javafx.application.*;
import javafx.fxml.*;
import javafx.scene.*;
import javafx.stage.*;

public class Main extends Application {
    private double x, y;

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("Pantalla_login_admin.fxml")); //Este sirve para cargar el archivo fxml
        primaryStage.setScene(new Scene(root));// esto sirve para que se muestre la pantalla
        primaryStage.initStyle(StageStyle.UNDECORATED); //Este sirve para quitar los botones de cerrar, minimizar y maximizar

        //Este sirve para mover la ventana
        root.setOnMousePressed(event -> {
            x = event.getSceneX();
            y = event.getSceneY();
        });
        root.setOnMouseDragged(event -> { //Este sirve para mover la ventana
            primaryStage.setX(event.getScreenX() - x);
            primaryStage.setY(event.getScreenY() - y);
        });
        primaryStage.show(); //Este sirve para mostrar la ventana
    }

    //Este es el main que se ejecuta al iniciar el programa
    public static void main(String[] args) {
        launch(args); //Este sirve para iniciar la aplicación
    }
}