package farmacia.proyecto_farmacia;

import javafx.application.Application;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;

public class Login extends Application {
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

}
