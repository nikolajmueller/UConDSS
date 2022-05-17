package STproject.Controllers;

import STproject.Main.Main;
import static STproject.Main.Main.symptoms;
import STproject.Models.*;
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
import javafx.scene.control.*;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javax.swing.JOptionPane;

public class SymptomController implements Initializable {

    @FXML
    private ComboBox bladderCapacityComboBox, otherComboBox;

    @FXML
    private TextField IEsTextField, UEsTextField,
            urinationTextField, nocturiaTextfield;

    @FXML
    private Label symptomsSaved;

    @FXML
    private Button btnSave, btnToTreatment, btnToEffect, btnToEvaluation;

    @FXML
    private Text saveTextVerify;

    @FXML
    private TextField cpr;

    @FXML
    private TextField name;

    @FXML
    private TextField age;

    @FXML
    private TextField gender;

    /**
     * Initializes the controller class
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        bladderCapacityComboBox.getItems().addAll("<200 ml", "200-400 ml", "400-500 ml", ">500 ml");
        otherComboBox.getItems().addAll("None", "Stroke", "Sclerosis");

        cpr.setText(Main.patient.getCprNumber());
        cpr.setStyle("-fx-text-fill: White;");
        name.setText(Main.patient.getName());
        name.setStyle("-fx-text-fill: White;");
        age.setText("" + Main.patient.getAge());
        age.setStyle("-fx-text-fill: White;");
        gender.setText(Main.patient.getGender());
        gender.setStyle("-fx-text-fill: White;");

    }

// funktion når man trykker på knappen "Save"
    public void btnSaveFunc(ActionEvent event) {

        try {
// gem værdier i @FXML bokse i klassen Symptoms
            symptoms.setBladderCapacity(bladderCapacityComboBox.getValue().toString());
            symptoms.setIEsPerDay(Integer.parseInt(IEsTextField.getText()));
            symptoms.setUEsPerDay(Integer.parseInt(UEsTextField.getText()));
            symptoms.setUrinationPerDay(Integer.parseInt(urinationTextField.getText()));
            symptoms.setNocturiaEpisodes(Integer.parseInt(nocturiaTextfield.getText()));
            symptoms.setOther(otherComboBox.getValue().toString());

// kald metode fra DatabaseHandler; gemmer værdier til databasen RET TIL INGEN ARGUMENTER
            DatabaseHandler.saveSymptonsToDb();

            Parent toLoginParent = FXMLLoader.load(getClass().getResource("/ressources/DashboardTreatmentStrategy.fxml"));
            Scene toLoginScene = new Scene(toLoginParent);
            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
            window.setScene(toLoginScene);
            window.centerOnScreen();
            window.show();

        } catch (Exception g) {
            JOptionPane.showMessageDialog(null, "Error saving symptoms to database");
        }

    }

    @FXML
    void logout(ActionEvent event) throws IOException {

        Parent toLoginParent = FXMLLoader.load(getClass().getResource("/ressources/LoginView.fxml"));
        Scene toLoginScene = new Scene(toLoginParent);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(toLoginScene);
        window.centerOnScreen();
        window.setTitle("UConDSS");
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
    void toEffect(ActionEvent event) throws IOException {

        Parent toSearchCreateParent = FXMLLoader.load(getClass().getResource("/ressources/DashboardEffectivenessScore.fxml"));
        Scene toSearchCreateScene = new Scene(toSearchCreateParent);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(toSearchCreateScene);
        window.centerOnScreen();
        window.show();
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

}
