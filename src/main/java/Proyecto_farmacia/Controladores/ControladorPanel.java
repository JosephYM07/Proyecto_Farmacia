package Proyecto_farmacia.Controladores;

import Proyecto_farmacia.Main;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;

public class ControladorPanel extends Main {
    private double x, y;
    @FXML
    private Button BotonHome;
    @FXML
    private Button BotonCajeros;
    @FXML
    private Button BotonAdmin;
    @FXML
    private Button BotonSalir;
    @FXML
    private Button BotonMinimizarApp;

    @FXML
    private Button BotonCerrarApp;

    @FXML
    private void HClicks() {
        BotonHome.setOnAction(event -> Pantalla_Home());
        BotonCajeros.setOnAction(event -> {
            try {
                Pantalla_Cajeros();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
        BotonAdmin.setOnAction(event -> {
            try {
                Pantalla_Admin();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
        BotonSalir.setOnAction(event -> {
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.close();
        });
        BotonCerrarApp.setOnAction(event -> {
            Stage stage = (Stage) BotonCerrarApp.getScene().getWindow();
            stage.close();
        });
        BotonMinimizarApp.setOnAction(event -> {
            Stage stage = (Stage) BotonMinimizarApp.getScene().getWindow();
            stage.setIconified(true);
        });
    }

    //Este sirve para mostrar la ventana
    private void Pantalla_Home() {

    }

    private void Pantalla_Cajeros() throws IOException {

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Proyecto_farmacia/Controlador/Pantalla_login_cajero.fxml"));
            Parent root = loader.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.initStyle(StageStyle.UNDECORATED);
            // Accede a la ventana actual y ciérrala
            Stage currentStage = (Stage) BotonCajeros.getScene().getWindow();
            currentStage.close();

            root.setOnMousePressed(event -> {
                x = event.getSceneX();
                y = event.getSceneY();
            });
            root.setOnMouseDragged(event -> {
                stage.setX(event.getScreenX() - x);
                stage.setY(event.getScreenY() - y);
            });
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void Pantalla_Admin() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Proyecto_farmacia/Controlador/Pantalla_login_admin.fxml"));
        Parent root = loader.load();
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.initStyle(StageStyle.UNDECORATED);
        // Accede a la ventana actual y ciérrala
        Stage currentStage = (Stage) BotonCajeros.getScene().getWindow();
        currentStage.close();

        root.setOnMousePressed(event -> {
            x = event.getSceneX();
            y = event.getSceneY();
        });
        root.setOnMouseDragged(event -> {
            stage.setX(event.getScreenX() - x);
            stage.setY(event.getScreenY() - y);
        });
        stage.show();

    }

}
