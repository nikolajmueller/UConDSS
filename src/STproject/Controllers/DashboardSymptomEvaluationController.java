package STproject.Controllers;

import STproject.Main.Main;
import static STproject.Main.Main.symptoms;
import STproject.Models.*;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.text.Text;

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

    public void btnSaveFunc() {

        try {
            symptoms.setBladderCapacity(bladderCapacityComboBox.getValue().toString());
            symptoms.setIEsPerDay(Integer.parseInt(IEsTextField.getText()));
            symptoms.setUEsPerDay(Integer.parseInt(UEsTextField.getText()));
            symptoms.setUrinationPerDay(Integer.parseInt(urinationTextField.getText()));
            symptoms.setNocturiaEpisodes(Integer.parseInt(nocturiaTextfield.getText()));
            symptoms.setOther(otherComboBox.getValue().toString());

            DatabaseHandler.saveSymptonsToDb(Main.patient.getCprNumber(), symptoms.getBladderCapacity(),
                    symptoms.getIEsPerDay(), symptoms.getUEsPerDay(), symptoms.getUrinationPerDay(),
                    symptoms.getNocturiaEpisodes(), symptoms.getOther());

            saveTextVerify.setText("Saved!");
        } catch (Exception g) {
            System.out.println("Error btnSaveFunc");
        }
    }
}
