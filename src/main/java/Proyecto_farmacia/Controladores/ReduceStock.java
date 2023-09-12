package Proyecto_farmacia.Controladores;

import java.sql.*;

public class ReduceStock {
    public static void main(String[] args) {
        String connectionUrl = "jdbc:mysql://localhost:3306/farmacia";
        String username = "root";
        String password = "admin";
        Connection connection = null;
        Statement statement = null;
        try {
            connection = DriverManager.getConnection(connectionUrl, username, password);
            statement = connection.createStatement();
            String query = "UPDATE products SET stock = stock - ? WHERE product_id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, Integer.parseInt("SroProd"));
            preparedStatement.setInt(2, Integer.parseInt("CodProd"));
            // Execute the query
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // Close the statement and connection
            try {
                if (statement != null) {
                    statement.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException se) {
                se.printStackTrace();
            }
        }
    }
}
