package Proyecto_farmacia.Controladores;

import Proyecto_farmacia.Controladores.Modulos.M_Admin;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.sql.*;

public class ControladorAdmin {
    private double x, y;
    //PANEL
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

    private void Pantalla_Cajeros() throws IOException {

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Proyecto_farmacia/Pantalla_login_cajero.fxml"));
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
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Proyecto_farmacia/Pantalla_login_admin.fxml"));
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

    // Método que se llama al hacer clic en el botón de validar
    //LOGIN
    private static final String URL = "jdbc:mysql://localhost:3306/farmacia";
    private static final String USUARIO = "root";
    private static final String CONTRASENA = "SoaD1725.";

    @FXML
    private TextField IngresoUsuarioAdmin;

    @FXML
    private PasswordField IngresoContraseñaAdmin;

    private List<M_Admin> administradores = new ArrayList<>();

    public ControladorAdmin() {
        // Cargar los administradores desde la base de datos al iniciar el controlador
        cargarAdministradoresDesdeBD();
    }

    private void cargarAdministradoresDesdeBD() {
        try (Connection conexion = DriverManager.getConnection(URL, USUARIO, CONTRASENA)) {
            String consulta = "SELECT UsuAdm, ConAdm FROM administradores";
            PreparedStatement consultaPreparada = conexion.prepareStatement(consulta);

            ResultSet resultado = consultaPreparada.executeQuery();

            while (resultado.next()) {
                String usuarioBD = resultado.getString("UsuAdm");
                String contrasenaBD = resultado.getString("ConAdm");

                M_Admin admin = new M_Admin(usuarioBD, contrasenaBD);
                administradores.add(admin);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void btnIniciarSesion() {
        String usuario = IngresoUsuarioAdmin.getText();
        String contrasena = IngresoContraseñaAdmin.getText();

        // Verificar las credenciales ingresadas por el usuario
        boolean accesoPermitido = false;
        for (M_Admin admin : administradores) {
            if (admin.getNombreUsuario().equals(usuario) && admin.getContrasena().equals(contrasena)) {
                accesoPermitido = true;
                break;
            }
        }

        if (accesoPermitido) {
            // Inicio de sesión exitoso
            mostrarMensaje("Inicio de sesión exitoso.");
            cargarVentanaInterfazPrincipal();
            // Realiza acciones adicionales aquí
        } else {
            // Mostrar un mensaje de error en caso de credenciales incorrectas
            mostrarMensaje("Inicio de sesión fallido. Verifica tus credenciales.");
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
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Proyecto_farmacia/Pantalla_info_admin.fxml"));
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


}


