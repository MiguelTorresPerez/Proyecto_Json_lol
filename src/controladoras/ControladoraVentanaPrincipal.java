package controladoras;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import ventanas.VentanaChart;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ControladoraVentanaPrincipal implements Initializable {

    @FXML
    TextField tf_nombreUsuario;

    @FXML
    Button bt_nombreUsuario;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        bt_nombreUsuario.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if(tf_nombreUsuario.getText().isEmpty()) {
                    Alert.AlertType alertAlertType;
                    Alert a = new Alert(AlertType.ERROR);
                    a.setTitle("Error");
                    a.setContentText("Introduce un nombre de usuario");
                    a.show();
                }
                    else {
                    tf_nombreUsuario.getScene().getWindow().hide();
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("../layouts/layout_ventana_chart.fxml"));
                    Parent root = null;
                    try {
                        root = loader.load();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    VentanaChart ventanaLogin = new VentanaChart();
                    ControladoraVentanaChart controladoraVentanaChart = loader.getController();
                    controladoraVentanaChart.setUser(tf_nombreUsuario.getText());
                    ventanaLogin.setScene(new Scene(root));
                }
            }
        });
    }

}
