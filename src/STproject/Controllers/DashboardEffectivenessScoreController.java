package STproject.Controllers;

import STproject.Main.Main;
import static STproject.Main.Main.*;
import STproject.Models.DatabaseHandler;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javax.swing.JOptionPane;

public class DashboardEffectivenessScoreController implements Initializable {

    @FXML
    private TextField TF_IEsPrevious, TF_UEsPrevious, TF_urinationPrevious, TF_nocturiaPrevious,
            TF_IEsPerDay, TF_UEsPerDay, TF_urination, TF_nocturia,
            TF_IEsPerDayScore, TF_UEsPerDayScore, TF_urinationScore, TF_nocturiaScore,
            TF_overallScore;

    @FXML
    private BarChart<String, Integer> barChart;

    @FXML
    private Button btnExpandGraph, btnToSymptoms, btnSave;

    @FXML
    private TextField cpr;

    @FXML
    private TextField name;

    @FXML
    private TextField age;

    @FXML
    private TextField gender;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        btnToSymptoms.setDisable(true);

        cpr.setText(Main.patient.getCprNumber());
        cpr.setStyle("-fx-text-fill: White;");
        name.setText(Main.patient.getName());
        name.setStyle("-fx-text-fill: White;");
        age.setText("" + Main.patient.getAge());
        age.setStyle("-fx-text-fill: White;");
        gender.setText(Main.patient.getGender());
        gender.setStyle("-fx-text-fill: White;");

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

        if (!TF_IEsPerDay.getText().isEmpty() && !TF_UEsPerDay.getText().isEmpty()
                && !TF_urination.getText().isEmpty() && !TF_nocturia.getText().isEmpty()) {

            symptomEffect.setPostIEs(Integer.parseInt(TF_IEsPerDay.getText()));
            symptomEffect.setPostUEs(Integer.parseInt(TF_UEsPerDay.getText()));
            symptomEffect.setPostUrination(Integer.parseInt(TF_urination.getText()));
            symptomEffect.setPostNocturia(Integer.parseInt(TF_nocturia.getText()));

// IEs
            symptomEffect.setIEsScore(((symptomEffect.getPostIEs() / symptoms.getIEsPerDay()) * 100) - 100);
            if (symptomEffect.getPostIEs() < symptoms.getIEsPerDay()) {
                TF_IEsPerDayScore.setText(String.format("%.1f", symptomEffect.getIEsScore()) + " %");
                TF_IEsPerDayScore.setStyle("-fx-text-inner-color: " + goodColor + ";");
            } else {
                TF_IEsPerDayScore.setText("+" + String.format("%.1f", symptomEffect.getIEsScore()) + " %");
                TF_IEsPerDayScore.setStyle("-fx-text-inner-color: " + badColor + ";");
            }

// UEs
            symptomEffect.setUEsScore(((symptomEffect.getPostUEs() / symptoms.getUEsPerDay()) * 100) - 100);
            if (symptomEffect.getPostUEs() < symptoms.getUEsPerDay()) {
                TF_UEsPerDayScore.setText(String.format("%.1f", symptomEffect.getUEsScore()) + " %");
                TF_UEsPerDayScore.setStyle("-fx-text-inner-color: " + goodColor + ";");
            } else {
                TF_UEsPerDayScore.setText("+" + String.format("%.1f", symptomEffect.getUEsScore()) + " %");
                TF_UEsPerDayScore.setStyle("-fx-text-inner-color: " + badColor + ";");
            }

// urination
            symptomEffect.setUrinationScore(((symptomEffect.getPostUrination() / symptoms.getUrinationPerDay()) * 100) - 100);
            if (symptomEffect.getPostUrination() < symptoms.getUrinationPerDay()) {
                TF_urinationScore.setText(String.format("%.1f", symptomEffect.getUrinationScore()) + " %");
                TF_urinationScore.setStyle("-fx-text-inner-color: " + goodColor + ";");
            } else {
                TF_urinationScore.setText("+" + String.format("%.1f", symptomEffect.getUrinationScore()) + " %");
                TF_urinationScore.setStyle("-fx-text-inner-color: " + badColor + ";");
            }

// nocturia
            symptomEffect.setNocturiaScore(((symptomEffect.getPostNocturia() / symptoms.getNocturiaEpisodes()) * 100) - 100);
            if (symptomEffect.getPostNocturia() < symptoms.getNocturiaEpisodes()) {
                TF_nocturiaScore.setText(String.format("%.1f", symptomEffect.getNocturiaScore()) + " %");
                TF_nocturiaScore.setStyle("-fx-text-inner-color: " + goodColor + ";");
            } else {
                TF_nocturiaScore.setText("+" + String.format("%.1f", symptomEffect.getNocturiaScore()) + " %");
                TF_nocturiaScore.setStyle("-fx-text-inner-color: " + badColor + ";");
            }

// overall effectiveness score
            symptomEffect.setOverallEffectivessScore((int) (symptomEffect.getIEsScore() + symptomEffect.getUEsScore()
                    + symptomEffect.getUrinationScore() + symptomEffect.getNocturiaScore()) / 4);

            if (symptomEffect.getOverallEffectivessScore() < 0) {
                TF_overallScore.setStyle("-fx-text-inner-color: " + goodColor + ";");
                TF_overallScore.setText("" + symptomEffect.getOverallEffectivessScore() + " %");
            } else {
                TF_overallScore.setStyle("-fx-text-inner-color: " + badColor + ";");
                TF_overallScore.setText("+" + symptomEffect.getOverallEffectivessScore() + " %");
            }

            DatabaseHandler.saveEffectToDb();

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
            btnSave.setDisable(false);
        } else {
            JOptionPane.showMessageDialog(null, "Fill in all field for new symptoms");
        }
    }

    public void expandGraph() {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/ressources/TreatmentExpandGraph.fxml"));
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.setTitle("UConDSS");
            stage.show();
        } catch (Exception e) {
            System.out.println("Error loading expanded graph");
        }
    }

    @FXML
    void logout(ActionEvent event) throws IOException {

        Parent toLoginParent = FXMLLoader.load(getClass().getResource("/ressources/LoginView.fxml"));
        Scene toLoginScene = new Scene(toLoginParent);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(toLoginScene);
        window.centerOnScreen();
        window.setTitle("UCon");
        window.show();
    }

    @FXML
    void selectNewPatient(ActionEvent event) throws IOException {

        Parent toSearchCreateParent = FXMLLoader.load(getClass().getResource("/ressources/SearchCreateView.fxml"));
        Scene toSearchCreateScene = new Scene(toSearchCreateParent);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(toSearchCreateScene);
        window.centerOnScreen();
        window.show();
    }

    @FXML
    void toTreatmentEvaluation(ActionEvent event) throws IOException {

        Parent toSearchCreateParent = FXMLLoader.load(getClass().getResource("/ressources/DashboardUconDataVisualization.fxml"));
        Scene toSearchCreateScene = new Scene(toSearchCreateParent);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(toSearchCreateScene);
        window.centerOnScreen();
        window.show();
    }

    @FXML
    void toTreatmentEvaluationBtnSave(ActionEvent event) throws IOException {

        if (!TF_IEsPerDay.getText().isEmpty() && !TF_UEsPerDay.getText().isEmpty()
                && !TF_urination.getText().isEmpty() && !TF_nocturia.getText().isEmpty()) {

            symptoms.setIEsPerDay((int) symptomEffect.getPostIEs());
            symptoms.setUEsPerDay((int) symptomEffect.getPostUEs());
            symptoms.setUrinationPerDay((int) symptomEffect.getPostUrination());
            symptoms.setNocturiaEpisodes((int) symptomEffect.getPostNocturia());

            DatabaseHandler.saveSymptonsToDb();

            Parent toSearchCreateParent = FXMLLoader.load(getClass().getResource("/ressources/DashboardUconDataVisualization.fxml"));
            Scene toSearchCreateScene = new Scene(toSearchCreateParent);
            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
            window.setScene(toSearchCreateScene);
            window.centerOnScreen();
            window.show();
        } else {
            JOptionPane.showMessageDialog(null, "Fill in all field for new symptoms");
        }
    }

    @FXML
    void toTreatment(ActionEvent event) throws IOException {

        Parent toSearchCreateParent = FXMLLoader.load(getClass().getResource("/ressources/DashboardTreatmentStrategy.fxml"));
        Scene toSearchCreateScene = new Scene(toSearchCreateParent);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(toSearchCreateScene);
        window.centerOnScreen();
        window.show();
    }

    @FXML
    void toSymptomEvaluation(ActionEvent event) throws IOException {

        Parent toSearchCreateParent = FXMLLoader.load(getClass().getResource("/ressources/DashboardSymptomEvaluation.fxml"));
        Scene toSearchCreateScene = new Scene(toSearchCreateParent);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(toSearchCreateScene);
        window.centerOnScreen();
        window.show();
    }

}
