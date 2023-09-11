package Proyecto_farmacia.Controladores.Modulos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexionMySQL {
    private static final String URL = "jdbc:mysql://localhost:3306/farmacia?autoReconnect=true";
    private static final String USUARIO = "root";
    private static final String CONTRASENA = "SoaD1725.";
    private static Connection conexion = null;

    public ConexionMySQL() {
        // Constructor: Puedes inicializar la conexión aquí si lo deseas
    }

    public static Connection obtenerConexion() {
        try {
            if (conexion == null) {
                Class.forName("com.mysql.cj.jdbc.Driver");
                conexion = DriverManager.getConnection(URL, USUARIO, CONTRASENA);
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return conexion;
    }
}
