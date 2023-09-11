package Proyecto_farmacia.Controladores;

import Proyecto_farmacia.Controladores.Modulos.M_Crud_Cajero;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.util.ResourceBundle;


import java.io.IOException;

public class ControladorInfoAdmin {
    private double x, y;
    @FXML
    private Button BotonAgregarCajero;
    @FXML
    private Button BotonIngresoMercaderia;
    @FXML
    private Button BotonRevisionFacturas;
    @FXML
    private Button BotonCerrarSesion;
    @FXML
    private Button BotonMinimizarApp;
    @FXML
    private Button BotonCerrarApp;

    @FXML
    private void HClicks() {
        BotonAgregarCajero.setOnAction(event -> Pantalla_Agregar_Cajero());
        BotonIngresoMercaderia.setOnAction(event -> {
            try {
                Pantlla_Agregar_Mercaderia();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
        BotonRevisionFacturas.setOnAction(event -> {
            try {
                Pantalla_Revisar_Ventas();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
        BotonCerrarSesion.setOnAction(event -> {
            try {
                Cerrar_Sesion();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
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
    private void Pantalla_Agregar_Cajero() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Proyecto_farmacia/Pantalla_Crud_Cajero_Admin.fxml"));
            Parent root = loader.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.initStyle(StageStyle.UNDECORATED);
            Stage currentStage = (Stage) BotonAgregarCajero.getScene().getWindow();
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

    private void Pantlla_Agregar_Mercaderia() throws IOException {

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Proyecto_farmacia/Pantalla_login_cajero.fxml"));
            Parent root = loader.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.initStyle(StageStyle.UNDECORATED);
            Stage currentStage = (Stage) BotonIngresoMercaderia.getScene().getWindow();
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

    private void Pantalla_Revisar_Ventas() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Proyecto_farmacia/Pantalla_login_admin.fxml"));
        Parent root = loader.load();
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.initStyle(StageStyle.UNDECORATED);
        Stage currentStage = (Stage) BotonRevisionFacturas.getScene().getWindow();
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

    private void Cerrar_Sesion() throws IOException {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Proyecto_farmacia/Pantalla_Home.fxml"));
            Parent root = loader.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.initStyle(StageStyle.UNDECORATED);
            Stage currentStage = (Stage) BotonCerrarSesion.getScene().getWindow();
            currentStage.close();
            root.setOnMousePressed(event2 -> {
                x = event2.getSceneX();
                y = event2.getSceneY();
            });

            root.setOnMouseDragged(event2 -> {
                stage.setX(event2.getScreenX() - x);
                stage.setY(event2.getScreenY() - y);
            });

            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }




}
