package Proyecto_farmacia.Controladores;

import Proyecto_farmacia.Controladores.Modulos.M_Crud_Cajero;
import Proyecto_farmacia.Controladores.Modulos.M_Crud_Productos;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
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

public class ControladorProductos {
    private double x, y;
    @FXML
    private Button BotonInicio;
    @FXML
    private Button BotonAgregarCajero;
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
        BotonAgregarCajero.setOnAction(event -> {
            try {
                Pantalla_agregar_cajero();
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

    private void Pantalla_agregar_cajero() throws IOException {

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Proyecto_farmacia/Pantalla_Crud_Cajero_Admin.fxml"));
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
    private TextField Id_producto;
    @FXML
    private TextField Nombre_Producto;
    @FXML
    private TextField Descripcion_Producto;
    @FXML
    private TextField Stock_Producto;
    @FXML
    private TextField Valor_Venta_Prodcuto;
    @FXML
    private TextField Proveedor_Producto;
    @FXML
    private RadioButton Iva_Si;
    @FXML
    private RadioButton Iva_No;
    private Connection connection;

    // Constructor del controlador
    public ControladorProductos() {
        // Inicializa la conexión en el constructor
        try {
            String url = "jdbc:mysql://localhost:3306/farmacia";
            String usuario = "root";
            String contraseña = "SoaD1725.";
            connection = DriverManager.getConnection(url, usuario, contraseña);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private ObservableList<M_Crud_Productos> productos = FXCollections.observableArrayList();

    public void btnBuscarProducto() {
        String idProductoStr = Id_producto.getText();
        String nombreProducto = Nombre_Producto.getText();

        // Verifica si ambos campos están vacíos
        if (idProductoStr.isEmpty() && nombreProducto.isEmpty()) {
            mostrarMensajeAdvertencia("Campos vacíos", "Por favor, ingresa al menos el ID o el Nombre para buscar.");
            return;
        }

        try {
            // Realiza la búsqueda basada en los campos no vacíos
            String sql;
            PreparedStatement statement;

            if (!idProductoStr.isEmpty()) {
                int idProducto = Integer.parseInt(idProductoStr);
                sql = "SELECT * FROM productos WHERE CodProd = ?";
                statement = connection.prepareStatement(sql);
                statement.setInt(1, idProducto);
            } else {
                sql = "SELECT * FROM productos WHERE NomProd = ?";
                statement = connection.prepareStatement(sql);
                statement.setString(1, nombreProducto);
            }

            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                // Llena el formulario con los datos del producto encontrado
                Nombre_Producto.setText(resultSet.getString("NomProd"));
                Descripcion_Producto.setText(resultSet.getString("DesProd"));
                Stock_Producto.setText(String.valueOf(resultSet.getInt("StoProd")));
                Valor_Venta_Prodcuto.setText(String.valueOf(resultSet.getDouble("ValVentProd")));
                Proveedor_Producto.setText(resultSet.getString("RucProvProd"));

                // Configura los RadioButtons Iva_Si e Iva_No según el valor de IvaProd
                boolean tieneIva = resultSet.getBoolean("IvaProd");
                if (tieneIva) {
                    Iva_Si.setSelected(true);
                    Iva_No.setSelected(false);
                } else {
                    Iva_Si.setSelected(false);
                    Iva_No.setSelected(true);
                }

                // Crear un objeto M_Crud_Productos con los datos del producto encontrado
                M_Crud_Productos productoEncontrado = new M_Crud_Productos(
                        resultSet.getInt("CodProd"),
                        resultSet.getString("NomProd"),
                        resultSet.getString("DesProd"),
                        resultSet.getInt("StoProd"),
                        resultSet.getDouble("ValVentProd"),
                        resultSet.getString("RucProvProd"),
                        tieneIva
                );

                // Limpiar la lista observable y agregar el producto encontrado
                productos.clear();
                productos.add(productoEncontrado);

                // Asignar los valores del producto a las celdas de la tabla
                colIdProducto.setCellValueFactory(new PropertyValueFactory<>("id_producto"));
                colNombreProducto.setCellValueFactory(new PropertyValueFactory<>("nombre_producto"));
                colDescripcionProducto.setCellValueFactory(new PropertyValueFactory<>("descripcion_producto"));
                colStock.setCellValueFactory(new PropertyValueFactory<>("stock_producto"));
                colPrecioProducto.setCellValueFactory(new PropertyValueFactory<>("valor_producto"));
                colIvaProducto.setCellValueFactory(new PropertyValueFactory<>("iva_producto"));
                colProveedorProducto.setCellValueFactory(new PropertyValueFactory<>("proveedor_producto"));

                // Actualizar la tabla con la lista observable
                tablaProductos.setItems(productos);

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

    public void btnAgregarProducto() {
        String Nombre_P = Nombre_Producto.getText();
        String Descripcion_P = Descripcion_Producto.getText();
        int Stock_P = Integer.parseInt(Stock_Producto.getText()); // Parsea el texto a entero
        double valor_V_P = Double.parseDouble(Valor_Venta_Prodcuto.getText()); // Parsea el texto a double
        String Proveedor_P = Proveedor_Producto.getText();

        try {
            String sql = "INSERT INTO productos (NomProd, DesProd, StoProd, ValVentProd, IvaProd, RucProvProd) VALUES (?, ?, ?, ?, ?, ?)";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, Nombre_P);
            statement.setString(2, Descripcion_P);
            statement.setInt(3, Stock_P);
            statement.setDouble(4, valor_V_P);

            // Define si tiene IVA o no (debes implementar la lógica aquí)
            // Por ejemplo, asumamos que tienes una variable booleana "tieneIva" para determinar si tiene IVA o no.
            boolean tieneIva = Iva_Si.isSelected(); // Usando el estado del RadioButton
            statement.setBoolean(5, tieneIva);

            statement.setString(6, Proveedor_P);

            statement.executeUpdate();
            mostrarMensajeAdvertencia("Producto agregado", "Se ha agregado un nuevo Producto.");
            btnLimpiarCampos();
        } catch (SQLException e) {
            mostrarMensajeAdvertencia("Error", "No se pudo agregar el Producto.");
            e.printStackTrace();
        }
    }

    public void btnEliminarProducto() {
        String idProductoStr = Id_producto.getText();

        if (idProductoStr.isEmpty()) {
            mostrarMensajeAdvertencia("Campo vacío", "Por favor, ingresa el ID del producto que deseas eliminar.");
            return;
        }

        try {
            int idProducto = Integer.parseInt(idProductoStr);
            String sql = "DELETE FROM productos WHERE CodProd = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, idProducto);
            int rowsAffected = statement.executeUpdate();

            if (rowsAffected > 0) {
                mostrarMensajeAdvertencia("Producto eliminado", "Se ha eliminado el producto con ID " + idProducto);
            } else {
                mostrarMensajeAdvertencia("Producto no encontrado", "No se encontró ningún producto con el ID especificado.");
            }
        } catch (NumberFormatException e) {
            mostrarMensajeAdvertencia("Formato incorrecto", "El ID del producto debe ser un número.");
        } catch (SQLException e) {
            mostrarMensajeAdvertencia("Error", "No se pudo eliminar el producto.");
            e.printStackTrace();
        }
    }

    public void btnActualizarProducto() {
        String idProductoStr = Id_producto.getText();

        if (idProductoStr.isEmpty()) {
            mostrarMensajeAdvertencia("Campo vacío", "Por favor, ingresa el ID del producto que deseas actualizar.");
            return;
        }

        try {
            int idProducto = Integer.parseInt(idProductoStr);
            String nombre = Nombre_Producto.getText();
            String descripcion = Descripcion_Producto.getText();
            int stock = Integer.parseInt(Stock_Producto.getText());
            double valorVenta = Double.parseDouble(Valor_Venta_Prodcuto.getText());
            String proveedor = Proveedor_Producto.getText();
            boolean tieneIva = Iva_Si.isSelected(); // Obtén el estado del RadioButton

            String sql = "UPDATE productos SET NomProd=?, DesProd=?, StoProd=?, ValVentProd=?, IvaProd=?, RucProvProd=? WHERE CodProd=?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, nombre);
            statement.setString(2, descripcion);
            statement.setInt(3, stock);
            statement.setDouble(4, valorVenta);
            statement.setBoolean(5, tieneIva);
            statement.setString(6, proveedor);
            statement.setInt(7, idProducto);

            int rowsAffected = statement.executeUpdate();

            if (rowsAffected > 0) {
                mostrarMensajeAdvertencia("Producto actualizado", "Se ha actualizado el producto con ID " + idProducto);
                btnLimpiarCampos();
            } else {
                mostrarMensajeAdvertencia("Producto no encontrado", "No se encontró ningún producto con el ID especificado.");
            }
        } catch (NumberFormatException e) {
            mostrarMensajeAdvertencia("Formato incorrecto", "El ID del producto y el stock deben ser números.");
        } catch (SQLException e) {
            mostrarMensajeAdvertencia("Error", "No se pudo actualizar el producto.");
            e.printStackTrace();
        }
    }

    //TABLA
    @FXML
    private TableView<M_Crud_Productos> tablaProductos;

    @FXML
    private TableColumn<M_Crud_Productos, Integer> colIdProducto;
    @FXML
    private TableColumn<M_Crud_Productos, String> colNombreProducto;
    @FXML
    private TableColumn<M_Crud_Productos, String> colDescripcionProducto;
    @FXML
    private TableColumn<M_Crud_Productos, Integer> colStock;
    @FXML
    private TableColumn<M_Crud_Productos, Double> colPrecioProducto;
    @FXML
    private TableColumn<M_Crud_Productos, Boolean> colIvaProducto;
    @FXML
    private TableColumn<M_Crud_Productos, String> colProveedorProducto;
    ObservableList<M_Crud_Productos> listaProductos = FXCollections.observableArrayList();


    public void btnPonerEnListaTodosLosProductos() {
        try {
            // Asignar los valores del producto a las celdas de la tabla
            colIdProducto.setCellValueFactory(new PropertyValueFactory<>("id_producto"));
            colNombreProducto.setCellValueFactory(new PropertyValueFactory<>("nombre_producto"));
            colDescripcionProducto.setCellValueFactory(new PropertyValueFactory<>("descripcion_producto"));
            colStock.setCellValueFactory(new PropertyValueFactory<>("stock_producto"));
            colPrecioProducto.setCellValueFactory(new PropertyValueFactory<>("valor_producto"));
            colIvaProducto.setCellValueFactory(new PropertyValueFactory<>("iva_producto"));
            colProveedorProducto.setCellValueFactory(new PropertyValueFactory<>("proveedor_producto"));

            // Crear una consulta SQL para obtener todos los productos
            String sql = "SELECT * FROM productos"; // Reemplaza "productos" con el nombre de la tabla
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery();

            // Limpiar la lista antes de agregar nuevos datos
            listaProductos.clear();

            // Recorrer los resultados de la consulta y agregarlos a la lista
            while (resultSet.next()) {
                int idProducto = resultSet.getInt("CodProd");
                String nombreProducto = resultSet.getString("NomProd");
                String descripcionProducto = resultSet.getString("DesProd");
                int stockProducto = resultSet.getInt("StoProd");
                double valorProducto = resultSet.getDouble("ValVentProd");
                boolean ivaProducto = resultSet.getBoolean("IvaProd");
                String proveedorProducto = resultSet.getString("RucProvProd");

                // Crear un objeto M_Crud_Productos con los datos del producto
                M_Crud_Productos producto = new M_Crud_Productos(
                        idProducto,
                        nombreProducto,
                        descripcionProducto,
                        stockProducto,
                        valorProducto,
                        proveedorProducto,
                        ivaProducto
                );
                listaProductos.add(producto);
            }

            // Asignar la lista observable a la tabla
            tablaProductos.setItems(listaProductos);
        } catch (SQLException e) {
            mostrarMensajeAdvertencia("Error", "No se pudieron cargar los productos desde la base de datos.");
            e.printStackTrace();
        }
    }

    public void btnSeleccionarProductosEnTabla() {
        // Obtén el producto seleccionado desde la tabla
        M_Crud_Productos productoSeleccionado = tablaProductos.getSelectionModel().getSelectedItem();

        // Verifica si hay un producto seleccionado o no
        if (productoSeleccionado != null) {
            // Copia la información del producto seleccionado a los campos de texto
            Id_producto.setText(String.valueOf(productoSeleccionado.getId_producto()));
            Nombre_Producto.setText(productoSeleccionado.getNombre_producto());
            Descripcion_Producto.setText(productoSeleccionado.getDescripcion_producto());
            Stock_Producto.setText(String.valueOf(productoSeleccionado.getStock_producto()));
            Valor_Venta_Prodcuto.setText(String.valueOf(productoSeleccionado.getValor_producto()));
            Proveedor_Producto.setText(productoSeleccionado.getProveedor_producto());

            // Configura los RadioButton Iva_Si e Iva_No según el valor de iva_producto
            if (productoSeleccionado.isIva_producto()) {
                Iva_Si.setSelected(true);
                Iva_No.setSelected(false);
            } else {
                Iva_Si.setSelected(false);
                Iva_No.setSelected(true);
            }
        } else {
            mostrarMensajeAdvertencia("Producto no seleccionado", "Por favor, selecciona un producto de la tabla.");
        }
    }

    public void btnLimpiarCampos() {
        // Limpia los campos de texto
        Id_producto.clear();
        Nombre_Producto.clear();
        Descripcion_Producto.clear();
        Stock_Producto.clear();
        Valor_Venta_Prodcuto.clear();
        Proveedor_Producto.clear();

        // Desmarca los RadioButtons Iva_Si e Iva_No
        Iva_Si.setSelected(false);
        Iva_No.setSelected(false);
    }

    private void mostrarMensajeAdvertencia(String titulo, String mensaje) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle(titulo);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }
}
