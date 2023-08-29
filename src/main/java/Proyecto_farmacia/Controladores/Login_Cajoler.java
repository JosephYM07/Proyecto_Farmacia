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
import javafx.scene.control.Button;

public class Login_Cajoler {

    @FXML
    private Button btnOverview;

    @FXML
    private Button btnCustomers;

    @FXML
    private Button btnCustomers1;

    @FXML
    private Button btnSettings;

    @FXML
    private Button btnSignout;

    public Login_Cajoler(Button btnOverview, Button btnCustomers, Button btnCustomers1, Button btnSettings, Button btnSignout) {
        this.btnOverview = btnOverview;
        this.btnCustomers = btnCustomers;
        this.btnCustomers1 = btnCustomers1;
        this.btnSettings = btnSettings;
        this.btnSignout = btnSignout;
    }

    @FXML
    public void handleClicks(ActionEvent actionEvent) {
        // Aquí maneja los clics de los botones
        if (actionEvent.getSource() == btnOverview) {

            // Código para el botón Overview
        } else if (actionEvent.getSource() == btnCustomers) {
            // Código para el botón Customers
        } else if (actionEvent.getSource() == btnCustomers1) {
            // Código para el botón Customers1
        } else if (actionEvent.getSource() == btnSettings) {
            // Código para el botón Settings
        } else if (actionEvent.getSource() == btnSignout) {
            // Código para el botón Signout
        }
    }
}

