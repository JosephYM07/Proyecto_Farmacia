package Proyecto_farmacia.Controladores;

import Proyecto_farmacia.Controladores.Modulos.M_Crud_Cajero;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

import java.io.IOException;


public class ControladorCrudCajeroAdmin {

    private double x, y;

    @FXML
    private Button BotonInicio;
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
        BotonInicio.setOnAction(event -> {
            try {
                Pantalla_Info_admin();
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

    private void Pantalla_Info_admin() throws IOException {

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Proyecto_farmacia/Pantalla_Info_admin.fxml"));
            Parent root = loader.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.initStyle(StageStyle.UNDECORATED);

            Stage currentStage = (Stage) BotonInicio.getScene().getWindow();
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

    //Mecanismo CRUD Cajeros
    @FXML
    private TextField Id_Cajero;
    @FXML
    private TextField Nombre_Cajero;
    @FXML
    private TextField Apellido_Cajero;
    @FXML
    private TextField Usuario_Cajero;
    @FXML
    private TextField Correo_Cajero;
    @FXML
    private TextField Telefono_Cajero;
    @FXML
    private TextField Direccion_Cajero;
    @FXML
    private TextField Password_Cajero;

    // Declara una variable de conexión global
    private Connection connection;

    // Constructor del controlador
    public ControladorCrudCajeroAdmin() {
        // Inicializa la conexión en el constructor
        try {
            String url = "jdbc:mysql://localhost:3306/farmacia"; // Cambia esto por la URL de tu base de datos MySQL
            String usuario = "root";
            String contraseña = "SoaD1725.";
            connection = DriverManager.getConnection(url, usuario, contraseña);
        } catch (SQLException e) {
            e.printStackTrace();
            // Maneja las excepciones de conexión aquí
        }
    }

    //Acciones Botones
    @FXML
    private void btnBuscarCajero() {
        // Obtiene la ID o el usuario a buscar
        String idCajero = Id_Cajero.getText();
        String usuarioCajero = Usuario_Cajero.getText();

        // Realiza la consulta SQL para buscar el cajero
        String query = "SELECT * FROM cajeros WHERE idCaj = ? OR UsuCaj = ?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, idCajero);
            preparedStatement.setString(2, usuarioCajero);
            ResultSet resultSet = preparedStatement.executeQuery();

            // Verifica si se encontró un resultado
            if (resultSet.next()) {
                mostrarMensajeAdvertencia("Validacion", "Se encontró el cajero.");
                Nombre_Cajero.setText(resultSet.getString("NomCaj"));
                Apellido_Cajero.setText(resultSet.getString("ApeCaj"));
                Correo_Cajero.setText(resultSet.getString("CorCaj"));
                Telefono_Cajero.setText(resultSet.getString("TelCaj"));
                Direccion_Cajero.setText(resultSet.getString("DirCaj"));
                Password_Cajero.setText(resultSet.getString("ConCaj"));
            } else {
                // Si no se encontró ningún resultado, puedes mostrar un mensaje de error o realizar otra acción
                // Puedes usar una etiqueta de texto para mostrar el mensaje de error, por ejemplo:
                // mensajeError.setText("No se encontró el cajero con el ID o usuario especificado.");
                mostrarMensajeAdvertencia("Error", "No se encontró el cajero con el ID o usuario especificado.");

            }
            // Cierra los recursos de la consulta
            resultSet.close();
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
            // Maneja las excepciones de SQL aquí
        }
    }

    @FXML
    private void btnAgregarCajero() {
        // Implementa la lógica para agregar un nuevo Cajero
        String nombre = Nombre_Cajero.getText();
        String apellido = Apellido_Cajero.getText();
        String direccion = Direccion_Cajero.getText();
        String correo = Correo_Cajero.getText();
        String telefono = Telefono_Cajero.getText();
        String usuario = Usuario_Cajero.getText();
        String contraseña = Password_Cajero.getText();

        try {
            String sql = "INSERT INTO cajeros (NomCaj, ApeCaj, DirCaj, CorCaj, TelCaj, UsuCaj, ConCaj) VALUES (?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, nombre);
            statement.setString(2, apellido);
            statement.setString(3, direccion);
            statement.setString(4, correo);
            statement.setString(5, telefono);
            statement.setString(6, usuario);
            statement.setString(7, contraseña);
            statement.executeUpdate();
            mostrarMensajeAdvertencia("Cajero agregado", "Se ha agregado un nuevo Cajero.");
            btnLimpiarCampos();
        } catch (SQLException e) {
            mostrarMensajeAdvertencia("Error", "No se pudo agregar el Cajero.");
            e.printStackTrace();
        }

    }

    @FXML
    private void btnEliminarCajero() {
        String idCajeroStr = Id_Cajero.getText();

        if (idCajeroStr.isEmpty()) {
            mostrarMensajeAdvertencia("Campo vacío", "Por favor, ingresa el ID del cajero que deseas eliminar.");
            return;
        }

        try {
            int idCajero = Integer.parseInt(idCajeroStr);
            String sql = "DELETE FROM cajeros WHERE idCaj = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, idCajero);
            int rowsAffected = statement.executeUpdate();

            if (rowsAffected > 0) {
                mostrarMensajeAdvertencia("Cajero eliminado", "Se ha eliminado el cajero con ID " + idCajero);
            } else {
                mostrarMensajeAdvertencia("Cajero no encontrado", "No se encontró ningún cajero con el ID especificado.");
            }
        } catch (NumberFormatException e) {
            mostrarMensajeAdvertencia("Formato incorrecto", "El ID del cajero debe ser un número.");
        } catch (SQLException e) {
            mostrarMensajeAdvertencia("Error", "No se pudo eliminar el cajero.");
            e.printStackTrace();
        }

    }

    @FXML
    private void btnActualizarCajero() {
        String idCajeroStr = Id_Cajero.getText();

        if (idCajeroStr.isEmpty()) {
            mostrarMensajeAdvertencia("Campo vacío", "Por favor, ingresa el ID del cajero que deseas actualizar.");
            return;
        }
        try {
            int idCajero = Integer.parseInt(idCajeroStr);
            String nombre = Nombre_Cajero.getText();
            String apellido = Apellido_Cajero.getText();
            String direccion = Direccion_Cajero.getText();
            String correo = Correo_Cajero.getText();
            String telefono = Telefono_Cajero.getText();
            String usuario = Usuario_Cajero.getText();
            String contraseña = Password_Cajero.getText();

            String sql = "UPDATE cajeros SET NomCaj=?, ApeCaj=?, DirCaj=?, CorCaj=?, TelCaj=?, UsuCaj=?, ConCaj=? WHERE IdCaj=?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, nombre);
            statement.setString(2, apellido);
            statement.setString(3, direccion);
            statement.setString(4, correo);
            statement.setString(5, telefono);
            statement.setString(6, usuario);
            statement.setString(7, contraseña);
            statement.setInt(8, idCajero);

            int rowsAffected = statement.executeUpdate();

            if (rowsAffected > 0) {
                mostrarMensajeAdvertencia("Cajero actualizado", "Se ha actualizado el cajero con ID " + idCajero);
                btnLimpiarCampos();
            } else {
                mostrarMensajeAdvertencia("Cajero no encontrado", "No se encontró ningún cajero con el ID especificado.");
            }
        } catch (NumberFormatException e) {
            mostrarMensajeAdvertencia("Formato incorrecto", "El ID del cajero debe ser un número.");
        } catch (SQLException e) {
            mostrarMensajeAdvertencia("Error", "No se pudo actualizar el cajero.");
            e.printStackTrace();
        }
    }

    @FXML
    private void btnPonerEnListaTodosLosCajeros() {

    }

    @FXML
    private void btnSeleccionarCajeroEnTabla() {

    }

    @FXML
    private void btnLimpiarCampos() {
        Id_Cajero.clear();
        Nombre_Cajero.clear();
        Apellido_Cajero.clear();
        Direccion_Cajero.clear();
        Correo_Cajero.clear();
        Telefono_Cajero.clear();
        Usuario_Cajero.clear();
        Password_Cajero.clear();
    }

    private void mostrarMensajeAdvertencia(String titulo, String mensaje) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle(titulo);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }


}
