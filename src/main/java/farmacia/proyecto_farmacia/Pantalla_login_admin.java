package farmacia.proyecto_farmacia;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;

public class Pantalla_login_admin {
    @FXML
    private VBox pnItems;
    @FXML
    private ImageView imageView;
    @FXML
    private Label label;

    @FXML
    private Button btnOverview;

    @FXML
    private Button btnCustomers;

    @FXML
    private Button btnCustomers1;

    @FXML
    private Button btnSettings;

    @FXML
    private Button btnSignout;

    @FXML
    public void handleClicks(ActionEvent actionEvent) {
        // Aquí maneja los clics de los botones
        if (actionEvent.getSource() == btnOverview) {

            // Código para el botón Overview
        } else if (actionEvent.getSource() == btnCustomers) {
            // Código para el botón Customers
        } else if (actionEvent.getSource() == btnCustomers1) {
            // Código para el botón Customers1
        } else if (actionEvent.getSource() == btnSettings) {
            // Código para el botón Settings
        } else if (actionEvent.getSource() == btnSignout) {
            // Código para el botón Signout
        }
    }
}
