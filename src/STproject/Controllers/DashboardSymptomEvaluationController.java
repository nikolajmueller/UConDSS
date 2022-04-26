package STproject.Controllers;

import static STproject.Main.Main.patient;
import static STproject.Main.Main.symptoms;
import STproject.Models.*;
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

public class DashboardSymptomEvaluationController implements Initializable {

    @FXML
    private ComboBox bladderCapacityComboBox, otherComboBox;

    @FXML
    private TextField IEsTextField, UEsTextField,
            urinationTextField, nocturiaTextfield;

    @FXML
    private Button btnSave;

    @FXML
    private Text saveTextVerify;

    /**
     * Initializes the controller class
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        bladderCapacityComboBox.getItems().addAll("<200 ml", "200-400 ml", "400-500 ml", ">500 ml");
        otherComboBox.getItems().addAll("None", "Stroke", "Sclerosis");

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
            System.out.println(patient.getCprNumber());
            DatabaseHandler.saveSymptonsToDb();
            /*
            Parent toTreatmentParent = FXMLLoader.load(getClass().getResource("/ressources/DashboardTreatmentStrategy.fxml"));
            Scene toTreatmentScene = new Scene(toTreatmentParent);
            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
            window.setScene(toTreatmentScene);
            window.show();
            window.centerOnScreen();
             */
        } catch (Exception g) {
            JOptionPane.showMessageDialog(null, "Error saving symptoms to database");
        }

    }

}
