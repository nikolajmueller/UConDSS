package STproject.Controllers;

import static STproject.Main.Main.*;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Popup;
import javafx.stage.Stage;

public class DashboardEffectivenessScoreController implements Initializable {

    @FXML
    private TextField TF_IEsPrevious, TF_UEsPrevious, TF_urinationPrevious, TF_nocturiaPrevious,
            TF_IEsPerDay, TF_UEsPerDay, TF_urination, TF_nocturia,
            TF_IEsPerDayScore, TF_UEsPerDayScore, TF_urinationScore, TF_nocturiaScore;

    @FXML
    private BarChart<String, Integer> barChart;

    @FXML
    private Button btnExpandGraph;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // dummy patientdata
        /*patient.setCprNumber("0205950237");
        patient.setName("Erik Eriksen");
        patient.setAge(26);
        patient.setGender("Male");
        symptoms.setIEsPerDay(4);
        symptoms.setUEsPerDay(7);
        symptoms.setUrinationPerDay(12);
        symptoms.setNocturiaEpisodes(3);
         */

// sæt PREVIOUS textfields
        TF_IEsPrevious.setText("" + symptoms.getIEsPerDay());
        TF_UEsPrevious.setText("" + symptoms.getUEsPerDay());
        TF_urinationPrevious.setText("" + symptoms.getUrinationPerDay());
        TF_nocturiaPrevious.setText("" + symptoms.getNocturiaEpisodes());

// BARCHART
        XYChart.Series<String, Integer> seriesPrevious = new XYChart.Series();
        seriesPrevious.setName("Pre symptoms");
        seriesPrevious.getData().add(new XYChart.Data("IEs", symptoms.getIEsPerDay()));
        seriesPrevious.getData().add(new XYChart.Data("UEs", symptoms.getUEsPerDay()));
        seriesPrevious.getData().add(new XYChart.Data("Urination", symptoms.getUrinationPerDay()));
        seriesPrevious.getData().add(new XYChart.Data("Nocturia Ep", symptoms.getNocturiaEpisodes()));
        barChart.setAnimated(false);

        barChart.getData().addAll(seriesPrevious);
    }

    public void determineEffect() {

        String goodColor = "#14B831";
        String badColor = "#F34E2B";

        symptomEffect.setPostIEs(Integer.parseInt(TF_IEsPerDay.getText()));
        symptomEffect.setPostUEs(Integer.parseInt(TF_UEsPerDay.getText()));
        symptomEffect.setPostUrination(Integer.parseInt(TF_urination.getText()));
        symptomEffect.setPostNocturia(Integer.parseInt(TF_nocturia.getText()));

// IEs
        if (symptomEffect.getPostIEs() < symptoms.getIEsPerDay()) {
            symptomEffect.setIEsScore(100 - ((symptomEffect.getPostIEs() / symptoms.getIEsPerDay()) * 100));
            TF_IEsPerDayScore.setText("-" + String.format("%.1f", symptomEffect.getIEsScore()) + " %");
            TF_IEsPerDayScore.setStyle("-fx-text-inner-color: " + goodColor + ";");
        } else if (symptomEffect.getPostIEs() > symptoms.getIEsPerDay()) {
            symptomEffect.setIEsScore(100 - ((symptoms.getIEsPerDay() / symptomEffect.getPostIEs()) * 100));
            TF_IEsPerDayScore.setText("+" + String.format("%.1f", symptomEffect.getIEsScore()) + " %");
            TF_IEsPerDayScore.setStyle("-fx-text-inner-color: " + badColor + ";");
        } else {
            symptomEffect.setIEsScore(0);
            TF_IEsPerDayScore.setText(symptomEffect.getIEsScore() + " %");
        }

// UEs
        if (symptomEffect.getPostUEs() < symptoms.getUEsPerDay()) {
            symptomEffect.setUEsScore(100 - ((symptomEffect.getPostUEs() / symptoms.getUEsPerDay()) * 100));
            TF_UEsPerDayScore.setText("-" + String.format("%.1f", symptomEffect.getUEsScore()) + " %");
            TF_UEsPerDayScore.setStyle("-fx-text-inner-color: " + goodColor + ";");
        } else if (symptomEffect.getPostUEs() > symptoms.getUEsPerDay()) {
            symptomEffect.setUEsScore(100 - ((symptoms.getUEsPerDay() / symptomEffect.getPostUEs()) * 100));
            TF_UEsPerDayScore.setText("+" + String.format("%.1f", symptomEffect.getUEsScore()) + " %");
            TF_UEsPerDayScore.setStyle("-fx-text-inner-color: " + badColor + ";");
        } else {
            symptomEffect.setUEsScore(0);
            TF_UEsPerDayScore.setText(symptomEffect.getUEsScore() + " %");
        }

// urination
        if (symptomEffect.getPostUrination() < symptoms.getUrinationPerDay()) {
            symptomEffect.setUrinationScore(100 - ((symptomEffect.getPostUrination() / symptoms.getUrinationPerDay()) * 100));
            TF_urinationScore.setText("-" + String.format("%.1f", symptomEffect.getUrinationScore()) + " %");
            TF_urinationScore.setStyle("-fx-text-inner-color: " + goodColor + ";");
        } else if (symptomEffect.getPostUrination() > symptoms.getUrinationPerDay()) {
            symptomEffect.setUrinationScore(100 - ((symptoms.getUrinationPerDay() / symptomEffect.getPostUrination()) * 100));
            TF_urinationScore.setText("+" + String.format("%.1f", symptomEffect.getUrinationScore()) + " %");
            TF_urinationScore.setStyle("-fx-text-inner-color: " + badColor + ";");
        } else {
            symptomEffect.setUrinationScore(0);
            TF_urinationScore.setText(symptomEffect.getUrinationScore() + " %");
        }

// nocturia
        if (symptomEffect.getPostNocturia() < symptoms.getNocturiaEpisodes()) {
            symptomEffect.setNocturiaScore(100 - ((symptomEffect.getPostNocturia() / symptoms.getNocturiaEpisodes()) * 100));
            TF_nocturiaScore.setText("-" + String.format("%.1f", symptomEffect.getNocturiaScore()) + " %");
            TF_nocturiaScore.setStyle("-fx-text-inner-color: " + goodColor + ";");
        } else if (symptomEffect.getPostNocturia() > symptoms.getNocturiaEpisodes()) {
            symptomEffect.setNocturiaScore(100 - ((symptoms.getNocturiaEpisodes() / symptomEffect.getPostNocturia()) * 100));
            TF_nocturiaScore.setText("+" + String.format("%.1f", symptomEffect.getNocturiaScore()) + " %");
            TF_nocturiaScore.setStyle("-fx-text-inner-color: " + badColor + ";");
        } else {
            symptomEffect.setNocturiaScore(0);
            TF_nocturiaScore.setText(symptomEffect.getNocturiaScore() + " %");
        }

        XYChart.Series<String, Integer> seriesEffect = new XYChart.Series();
        seriesEffect.setName("Post symptoms");
        seriesEffect.getData().add(new XYChart.Data("IEs", symptomEffect.getPostIEs()));
        seriesEffect.getData().add(new XYChart.Data("UEs", symptomEffect.getPostUEs()));
        seriesEffect.getData().add(new XYChart.Data("Urination", symptomEffect.getPostUrination()));
        seriesEffect.getData().add(new XYChart.Data("Nocturia Ep", symptomEffect.getPostNocturia()));

        if (barChart.getData().size() > 1) {
            barChart.getData().remove(1);
            barChart.getData().addAll(seriesEffect);
        } else {
            barChart.getData().addAll(seriesEffect);
        }
    }
// "/ressources/TreatmentExpandGraph.fxml"
    public void expandGraph() {
       
    }

}
