package Proyecto_farmacia.Controladores;

import Proyecto_farmacia.Controladores.Modulos.M_Admin;
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
import java.util.ArrayList;
import java.util.List;

public class ControladorFactCajero {
    private double x, y;
    @FXML
    private Button Cerrar_sesion;

    @FXML
    private void HClicks() {
        Cerrar_sesion.setOnAction(event -> {
            try {
                Cerrar_Sesion();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
    }

    private void Cerrar_Sesion() throws IOException {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Proyecto_farmacia/Pantalla_Home.fxml"));
            Parent root = loader.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.initStyle(StageStyle.UNDECORATED);
            Stage currentStage = (Stage) Cerrar_sesion.getScene().getWindow();
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
    private TextField Ingreso_cajero;
    @FXML
    private TextField Ingreso_producto;

    @FXML
    private TextField Ingreso_cantidad;
    @FXML
    private TextField Ingreso_ci;
    @FXML
    private TextField Precio;
    @FXML
    private TextField Ingreso_nombre_cliente;
    @FXML
    private TextField Ingreso_fecha_factura;
    @FXML
    private TextField Ingreso_subtotal_factura;
    @FXML
    private TextField Ingreso_iva_factura;
    @FXML
    private TextField Ingreso_total_factura;

    private Connection connection;
    private static final String URL = "jdbc:mysql://localhost:3306/farmacia";
    private static final String USUARIO = "root";
    private static final String CONTRASENA = "admin";

    private List<M_Fact_Cajero> productos = new ArrayList<>();
    public ControladorFactCajero() {
        cargarProductosDesdeBD();
    }
    private void cargarProductosDesdeBD() {
        try (Connection conexion = DriverManager.getConnection(URL, USUARIO, CONTRASENA)) {
            String consulta = "SELECT NomProd, StoProd,ValVentProd FROM productos";
            PreparedStatement consultaPreparada = conexion.prepareStatement(consulta);

            ResultSet resultado = consultaPreparada.executeQuery();

            while (resultado.next()) {
                String nombreP = resultado.getString("NomProd");
                String cantidadP = resultado.getString("StoProd");
                String Precio = resultado.getString("ValVentProd");

                M_Fact_Cajero product = new M_Fact_Cajero(nombreP,cantidadP,Precio );
                productos.add(product);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //Acciones Botones
    @FXML
    private void boton_buscar_Producto() {
        String idProdStr = Ingreso_producto.getText();
        String CantProd = Ingreso_cantidad.getText();

        // Verifica si ambos campos están vacíos
        if (idProdStr.isEmpty() && CantProd.isEmpty()) {
            mostrarMensajeAdvertencia("Campos vacíos", "Por favor, ingresa al menos el Nombre o la cantidad para buscar.");
            return;
        }

        try {
            // Realiza la búsqueda basada en los campos no vacíos
            String sql;
            PreparedStatement statement;

            if (!idProdStr.isEmpty()) {
                int idProd = Integer.parseInt(idProdStr);
                sql = "SELECT * FROM cajeros WHERE CodProd = ?";
                statement = connection.prepareStatement(sql);
                statement.setInt(1, idProd);
            } else {
                sql = "SELECT * FROM cajeros WHERE NomProd = ?";
                statement = connection.prepareStatement(sql);
                statement.setString(1, CantProd);
            }

            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                // Se llena el formulario con los datos del producto encontrado
                Ingreso_producto.setText(resultSet.getString("NomProd"));
                //Ingreso_cajero.setText(resultSet.getString("idCaj"));
                //Ingreso_nombre_cliente.setText(resultSet.getString("DesProd"));
                Ingreso_cantidad.setText(resultSet.getString("StoProd"));
                Precio.setText(resultSet.getString("ValVentProd"));
                // Se crea un objeto M_Crud_Cajero con los datos del cajero encontrado
                M_Fact_Cajero productoEncontrado = new M_Fact_Cajero(
                        resultSet.getString("NomProd"),
                        resultSet.getString("StoProd"),
                        resultSet.getString("ValVentProd")
                        );
                // Limpia la lista observable y agrega el cajero encontrado
                productos.clear();
                productos.add(productoEncontrado);
                // Actualiza la tabla con la lista observable
                Tabla_registro_productos.setItems((ObservableList<M_Fact_Cajero>) productos);
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
    private void boton_seleccionar_Porductos() {
        // Obtén la fila seleccionada desde la tabla
        M_Fact_Cajero productoSeleccionado = Tabla_registro_productos.getSelectionModel().getSelectedItem();

        // Se verifica si hay una fila seleccionada o no
        if (productoSeleccionado != null) {
            // Se copia la información del producto seleccionado a los campos de texto
            Ingreso_producto.setText(String.valueOf(productoSeleccionado.getId_Prod()));
            //Desc_Producto.setText(productoSeleccionado.getDesc_Producto());
            Ingreso_cantidad.setPrefColumnCount(productoSeleccionado.getCantidad_Producto());
            Precio.setPrefColumnCount(productoSeleccionado.getPrecio());
        } else {
            mostrarMensajeAdvertencia("Producto no seleccionado", "Por favor, selecciona un producto de la tabla.");
        }
    }

    //MOSTRAR DATOS EN TABLA
    @FXML
    private TableView<M_Fact_Cajero> Tabla_registro_productos;
    @FXML
    private TableColumn<M_Fact_Cajero, Integer> Col_ide_registro;
    @FXML
    private TableColumn<M_Fact_Cajero, String> Col_descripcion_registro;
    @FXML
    private TableColumn<M_Fact_Cajero, Integer> Col_stock_registro;
    @FXML
    private TableColumn<M_Fact_Cajero, Integer> Col_precio_registro;
    private ObservableList<M_Fact_Cajero> listaproductos = FXCollections.observableArrayList();

    @FXML
    private void boton_nueva_factura() {
        try {
            // Asignar los valores del producto a las celdas de la tabla
            Col_ide_registro.setCellValueFactory(new PropertyValueFactory<>("id"));
            Col_descripcion_registro.setCellValueFactory(new PropertyValueFactory<>("descripcion"));
            Col_stock_registro.setCellValueFactory(new PropertyValueFactory<>("stock"));
            Col_precio_registro.setCellValueFactory(new PropertyValueFactory<>("precio"));
            // Crear una consulta SQL para obtener todos los productos
            String sql = "SELECT * FROM productos"; // Reemplaza "prosuctos" con el nombre de la tabla
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery();

            // Limpiar la lista antes de agregar nuevos datos
            listaproductos.clear();

            // Asignar la lista observable a la tabla
            Tabla_registro_productos.setItems(listaproductos);
        } catch (SQLException e) {
            mostrarMensajeAdvertencia("Error", "No se pudieron cargar los productos desde la base de datos.");
            e.printStackTrace();
        }
    }

    @FXML
    private void boton_imprimir_factura() {
        try {
            // Insertar una nueva fila en la tabla de facturas
            String insertFacturaSQL = "INSERT INTO cabecerasfacturas (CI_Cliente,Id_Cajero,Nombre_Cliente,Fecha_Factura,Precio,Iva,Total_Fac) VALUES (?, ?, ?,?,?,?,?)";
            PreparedStatement insertStatement = connection.prepareStatement(insertFacturaSQL, Statement.RETURN_GENERATED_KEYS);
            insertStatement.setString(1, Ingreso_ci.getText());
            insertStatement.setString(2, Ingreso_cajero.getText());
            insertStatement.setString(3, Ingreso_nombre_cliente.getText());
            insertStatement.setString(4, Ingreso_fecha_factura.getText());
            insertStatement.setString(5, Precio.getText());
            insertStatement.setString(6, Ingreso_iva_factura.getText());
            insertStatement.setString(7, Ingreso_total_factura.getText());
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
            Ingreso_ci.clear();
            Ingreso_cajero.clear();
            Ingreso_nombre_cliente.clear();
            Ingreso_fecha_factura.clear();
            Precio.clear();
            Ingreso_iva_factura.clear();
            Ingreso_total_factura.clear();

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