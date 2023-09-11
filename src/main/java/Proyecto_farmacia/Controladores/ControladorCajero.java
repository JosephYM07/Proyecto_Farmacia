package Proyecto_farmacia.Controladores;

import Proyecto_farmacia.Controladores.Modulos.M_Cajero;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class ControladorCajero {
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
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Proyecto_farmacia/Pantalla_Home.fxml"));
            Parent root = loader.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.initStyle(StageStyle.UNDECORATED);
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

    private void Pantalla_Cajeros() throws IOException {

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Proyecto_farmacia/Pantalla_login_cajero.fxml"));
            Parent root = loader.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.initStyle(StageStyle.UNDECORATED);
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
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Proyecto_farmacia/Pantalla_login_admin.fxml"));
        Parent root = loader.load();
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.initStyle(StageStyle.UNDECORATED);
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

    //LOGIN CAJERO
    @FXML
    private TextField IngresoUsuarioCajero;

    @FXML
    private PasswordField IngresoContraseñaCajero;

    private List<M_Cajero> cajeros = new ArrayList<>();

    public ControladorCajero() {
        cargarCajerosDesdeBD();
    }

    private void cargarCajerosDesdeBD() {
        String URL = "jdbc:mysql://localhost:3306/farmacia";
        String USUARIO = "root";
        String CONTRASENA = "SoaD1725.";

        try (Connection conexion = DriverManager.getConnection(URL, USUARIO, CONTRASENA)) {
            String consulta = "SELECT UsuCaj, ConCaj FROM cajeros";
            PreparedStatement consultaPreparada = conexion.prepareStatement(consulta);

            ResultSet resultado = consultaPreparada.executeQuery();

            while (resultado.next()) {
                String usuarioBD = resultado.getString("UsuCaj");
                String contrasenaBD = resultado.getString("ConCaj");

                M_Cajero cajero = new M_Cajero(usuarioBD, contrasenaBD);
                cajeros.add(cajero);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void btnIniciarSesionCajero() {
        String usuario = IngresoUsuarioCajero.getText();
        String contrasena = IngresoContraseñaCajero.getText();

        boolean accesoPermitido = false;
        for (M_Cajero cajero : cajeros) {
            if (cajero.getNombreUsuario().equals(usuario) && cajero.getContrasena().equals(contrasena)) {
                accesoPermitido = true;
                break;
            }
        }

        if (accesoPermitido) {
            // Inicio de sesión exitoso
            mostrarMensaje("Inicio de sesión exitoso.");
            System.out.println("Inicio de sesión exitoso.");
            cargarVentanaInterfazPrincipal();
        } else {
            mostrarMensaje("Inicio de sesión fallido. Verifica tus credenciales.");
            System.out.println("Inicio de sesión fallido. Verifica tus credenciales.");
        }
    }

    private void mostrarMensaje(String mensaje) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Mensaje");
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }

    private void cargarVentanaInterfazPrincipal() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Proyecto_farmacia/Pantalla_Fac_Cajero.fxml"));
            Parent root = loader.load();

            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.initStyle(StageStyle.UNDECORATED);

            Stage currentStage = (Stage) IngresoUsuarioCajero.getScene().getWindow();
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
}

