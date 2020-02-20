package ventanas;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;


public class VentanaChart extends Stage {
    public VentanaChart() {
        initGUI();
    }
    private void initGUI() {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("../layouts/layout_ventana_principal.fxml"));
            Scene scene = new Scene(root, 800, 600);
            this.setScene(scene);
            this.setTitle("Grafica");
            this.initStyle(StageStyle.DECORATED);
            this.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
}}

