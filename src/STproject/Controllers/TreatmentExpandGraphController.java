package STproject.Controllers;

import static STproject.Main.Main.symptoms;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;

import javafx.fxml.Initializable;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.XYChart;

public class TreatmentExpandGraphController implements Initializable {

    @FXML
    private BarChart<String, Integer> barChart;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        XYChart.Series<String, Integer> seriesPrevious = new XYChart.Series();
        seriesPrevious.setName("Pre symptoms");
        seriesPrevious.getData().add(new XYChart.Data("IEs", symptoms.getIEsPerDay()));
        seriesPrevious.getData().add(new XYChart.Data("UEs", symptoms.getUEsPerDay()));
        seriesPrevious.getData().add(new XYChart.Data("Urination", symptoms.getUrinationPerDay()));
        seriesPrevious.getData().add(new XYChart.Data("Nocturia Ep", symptoms.getNocturiaEpisodes()));
        barChart.setAnimated(false);

        barChart.getData().addAll(seriesPrevious);
    }
}
