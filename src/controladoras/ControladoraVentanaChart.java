package controladoras;

import conexion.Conexion;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.XYChart;
import pojos.Campeon;
import pojos.Maestria;
import pojos.MaestriaUser;

import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.ResourceBundle;

public class ControladoraVentanaChart implements Initializable {
    @FXML
    BarChart chart;

    private String user;
    private ArrayList<Campeon> campeones;
    private ArrayList<Maestria> maestria;
    private ArrayList<MaestriaUser> aProcesado;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void setUser(String user) {
        this.user = user;
        realizarConexion();
        rellenarArrayProcesado();
        rellenarChart();
    }

    private void rellenarArrayProcesado() {
        aProcesado = new ArrayList<>();
        for (int i = 0; i < campeones.size(); i++) {
            for (int j = 0; j < maestria.size(); j++) {
                if (campeones.get(i).getId() == maestria.get(j).getId()) {
                    aProcesado.add(new MaestriaUser(campeones.get(i).getNombre(),maestria.get(j).getPoints()));
                }
            }}
    }

    private void rellenarChart() {
        XYChart.Series<String,Integer> chartContent = new XYChart.Series();
        for (MaestriaUser m : aProcesado) {
            chartContent.getData().add(new XYChart.Data<>(m.getNombre(),m.getPoints()));
        }
        chart.getData().add(chartContent);
    }

    private void realizarConexion() {
        Conexion c = new Conexion(user);
        campeones = c.getArrayCampeones();
        maestria = c.getArrayMaestrias();
    }
}
