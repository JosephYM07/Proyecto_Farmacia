/*package Proyecto_farmacia.Controladores;

public class Login_Cajero {
    private TextField cedulaTXT;
    private PasswordField contraseñaTXT;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        BorderPane login = new BorderPane();
        login.setPrefSize(1400, 750);

        VBox centerBox = new VBox(10);
        centerBox.setStyle("-fx-padding: 20px;");
        centerBox.setAlignment(Pos.CENTER);

        cedulaTXT = new TextField();
        contraseñaTXT = new PasswordField();

        Button iniciarSesiónButton = new Button("Iniciar Sesión");
        Button salirBNT = new Button("Salir");

        iniciarSesiónButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                login();
            }

            private void login() {
            }
        });

        salirBNT.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                primaryStage.close();
            }
        });

        centerBox.getChildren().addAll(new Label("Cédula:"), cedulaTXT, new Label("Contraseña:"), contraseñaTXT, iniciarSesiónButton, salirBNT);

        login.setCenter(centerBox);

        Scene scene = new Scene(login);
        primaryStage.setTitle("Iniciar Sesión");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

}*/
//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package Proyecto_farmacia.Controladores;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;

public class ControladorCajero extends ControladorPanel {
    @FXML
    private void Pantalla_Cajero() throws IOException {
        // Aquí puedes agregar el código específico de Pantalla_Admin
    }

}

