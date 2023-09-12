package Proyecto_farmacia.Controladores;

import Proyecto_farmacia.Controladores.Modulos.M_Crud_Cajero;
import Proyecto_farmacia.Controladores.Modulos.M_Fact_Cajero;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.sql.*;

public class ControladorFactCajero {
    private double x, y;

    @FXML
    private Button Overview;
    @FXML
    private Button BotonMinimizarApp;
    @FXML
    private Button BotonCerrarApp;
    @FXML
    private Button BotonCerrarSesion;

    @FXML
    private void HClicks() {
        Overview.setOnAction(event -> {
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

            Stage currentStage = (Stage) Overview.getScene().getWindow();
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
    private TextField Id_Prod;
    @FXML
    private TextField Desc_Producto;
    @FXML
    private TextField Nombre_Producto;
    @FXML
    private TextField Cantidad_Producto;
    @FXML
    private TextField CI_Cliente;
    @FXML
    private TextField Precio;
    @FXML
    private TextField Nombre_Cliente;
    @FXML
    private TextField Fecha_Factura;
    @FXML
    private TextField Subtotal;
    @FXML
    private TextField Iva;
    @FXML
    private TextField Total_Fac;

    private Connection connection;

    public ControladorFactCajero() {
        try {
            String url = "jdbc:mysql://localhost:3306/farmacia";
            String usuario = "root";
            String contraseña = "admin1";
            connection = DriverManager.getConnection(url, usuario, contraseña);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //Acciones Botones
    private ObservableList<M_Fact_Cajero> productos = FXCollections.observableArrayList();

    @FXML
    private void btnBuscarproducto() {
        String idCajeroStr = Id_Prod.getText();
        String usuarioCajero = Nombre_Producto.getText();

        // Verifica si ambos campos están vacíos
        if (idCajeroStr.isEmpty() && usuarioCajero.isEmpty()) {
            mostrarMensajeAdvertencia("Campos vacíos", "Por favor, ingresa al menos el ID o el Usuario para buscar.");
            return;
        }

        try {
            // Realiza la búsqueda basada en los campos no vacíos
            String sql;
            PreparedStatement statement;

            if (!idCajeroStr.isEmpty()) {
                int idCajero = Integer.parseInt(idCajeroStr);
                sql = "SELECT * FROM cajeros WHERE CodProd = ?";
                statement = connection.prepareStatement(sql);
                statement.setInt(1, idCajero);
            } else {
                sql = "SELECT * FROM cajeros WHERE NomProd = ?";
                statement = connection.prepareStatement(sql);
                statement.setString(1, usuarioCajero);
            }

            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                // Se llena el formulario con los datos del producto encontrado
                Id_Prod.setText(resultSet.getString("CodProd"));
                Id_Cajero.setText(resultSet.getString("idCaj"));
                Desc_Producto.setText(resultSet.getString("DesProd"));
                Cantidad_Producto.setText(resultSet.getString("StoProd"));
                Precio.setText(resultSet.getString("ValVentProd"));
                // Se crea un objeto M_Crud_Cajero con los datos del cajero encontrado
                M_Fact_Cajero productoEncontrado = new M_Fact_Cajero(
                        resultSet.getInt("CodProd"),
                        resultSet.getInt("idCaj"),
                        resultSet.getString("DesProd"),
                        resultSet.getString("StoProd"),
                        resultSet.getString("ValVentProd"),
                        resultSet.getString("Id_Prod"),
                        resultSet.getString("NomProd"));


                // Limpia la lista observable y agrega el cajero encontrado
                productos.clear();
                productos.add(productoEncontrado);


                // Actualiza la tabla con la lista observable
                tablaproductos.setItems(productos);

            } else {
                mostrarMensajeAdvertencia("Producto no encontrado", "No se encontró ningún producto con los datos especificados.");
            }
            mostrarMensajeAdvertencia("Validación Correcta", "El producto ha sido encontrado");

        } catch (NumberFormatException e) {
            mostrarMensajeAdvertencia("Formato incorrecto", "El ID del producto debe ser un número.");
        } catch (SQLException e) {
            mostrarMensajeAdvertencia("Error", "No se pudo buscar el producto.");
            e.printStackTrace();
        }
    }

    @FXML
    private void btnSeleccionarFactEnTabla() {
        // Obtén la fila seleccionada desde la tabla
        M_Fact_Cajero productoSeleccionado = tablaproductos.getSelectionModel().getSelectedItem();

        // Se verifica si hay una fila seleccionada o no
        if (productoSeleccionado != null) {
            // Se copia la información del producto seleccionado a los campos de texto
            Id_Prod.setText(String.valueOf(productoSeleccionado.getId_Prod()));
            Desc_Producto.setText(productoSeleccionado.getDesc_Producto());
            Cantidad_Producto.setPrefColumnCount(productoSeleccionado.getCantidad_Producto());
            Precio.setPrefColumnCount(productoSeleccionado.getPrecio());
        } else {
            mostrarMensajeAdvertencia("Producto no seleccionado", "Por favor, selecciona un producto de la tabla.");
        }
    }

    //MOSTRAR DATOS EN TABLA
    @FXML
    private TableView<M_Fact_Cajero> tablaproductos;
    @FXML
    private TableColumn<M_Fact_Cajero, Integer> colCodProd;
    @FXML
    private TableColumn<M_Fact_Cajero, String> colDesProd;
    @FXML
    private TableColumn<M_Fact_Cajero, Integer> colStoProd;
    @FXML
    private TableColumn<M_Fact_Cajero, Integer> colValVentProd;
    private ObservableList<M_Fact_Cajero> listaproductos = FXCollections.observableArrayList();

    @FXML
    private void btnPonerEnListaTodosLosCajeros() {
        try {
            // Asignar los valores del producto a las celdas de la tabla
            colCodProd.setCellValueFactory(new PropertyValueFactory<>("id"));
            colDesProd.setCellValueFactory(new PropertyValueFactory<>("descripcion"));
            colStoProd.setCellValueFactory(new PropertyValueFactory<>("stock"));
            colValVentProd.setCellValueFactory(new PropertyValueFactory<>("precio"));
            // Crear una consulta SQL para obtener todos los productos
            String sql = "SELECT * FROM productos"; // Reemplaza "prosuctos" con el nombre de la tabla
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery();

            // Limpiar la lista antes de agregar nuevos datos
            listaproductos.clear();

            // Asignar la lista observable a la tabla
            tablaproductos.setItems(listaproductos);
        } catch (SQLException e) {
            mostrarMensajeAdvertencia("Error", "No se pudieron cargar los productos desde la base de datos.");
            e.printStackTrace();
        }
    }

    @FXML
    private void btnNuevaFactura() {
        try {
            // Insertar una nueva fila en la tabla de facturas
            String insertFacturaSQL = "INSERT INTO cabecerasfacturas (CI_Cliente,Id_Cajero,Nombre_Cliente,Fecha_Factura,Precio,Iva,Total_Fac) VALUES (?, ?, ?,?,?,?,?)";
            PreparedStatement insertStatement = connection.prepareStatement(insertFacturaSQL, Statement.RETURN_GENERATED_KEYS);
            insertStatement.setString(1, CI_Cliente.getText());
            insertStatement.setString(2, Id_Cajero.getText());
            insertStatement.setString(3, Nombre_Cliente.getText());
            insertStatement.setString(4, Fecha_Factura.getText());
            insertStatement.setString(5, Precio.getText());
            insertStatement.setString(6, Iva.getText());
            insertStatement.setString(7, Total_Fac.getText());
            insertStatement.executeUpdate();

            // Obtener el ID de la factura recién creada
            ResultSet generatedKeys = insertStatement.getGeneratedKeys();
            int facturaID = -1;
            if (generatedKeys.next()) {
                facturaID = generatedKeys.getInt(1);
            }

            // Agregar productos a la factura (debes adaptar esto según cómo se almacenan los productos en tu sistema)
            String insertDetalleFacturaSQL = "INSERT INTO detallesfacturas ( Id_Prod, Cantidad_Producto, Precio_Unitario, Iva, Total) VALUES (?, ?, ?, ?,?)";
            PreparedStatement detalleStatement = connection.prepareStatement(insertDetalleFacturaSQL);
            detalleStatement.setInt(1, facturaID);

            // Debes obtener los datos del producto desde tu tabla de productos o desde donde los tengas
            int productoID = 0; // Reemplaza 1 con el ID del producto seleccionado
            int cantidad = 0; // Reemplaza 5 con la cantidad del producto seleccionado
            double precioUnitario = 0.0; // Reemplaza 10.0 con el precio unitario del producto seleccionado
            double total = 0; // Reemplaza 1 con el ID del producto seleccionado
            double iva = 0; // Reemplaza 5 con la cantidad del producto seleccionado

            detalleStatement.setInt(1, productoID);
            detalleStatement.setInt(2, cantidad);
            detalleStatement.setDouble(3, precioUnitario);
            detalleStatement.setDouble(4, iva);
            detalleStatement.setDouble(5, total);

            detalleStatement.executeUpdate();

            // Limpia los campos de la nueva factura
            CI_Cliente.clear();
            Id_Cajero.clear();
            Nombre_Cliente.clear();
            Fecha_Factura.clear();
            Precio.clear();
            Iva.clear();
            Total_Fac.clear();

        } catch (SQLException e) {
            mostrarMensajeAdvertencia("Error", "No se pudo crear la factura.");
            e.printStackTrace();
        }
    }
    private void mostrarMensajeAdvertencia (String titulo, String mensaje){
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle(titulo);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }
}