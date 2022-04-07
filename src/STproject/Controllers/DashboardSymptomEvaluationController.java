package STproject.Controllers;

import STproject.Models.DatabaseHandler;
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
        String patientName = "John Doe"; // dummy
        String patientCPR = "040875-1337"; // dummy

        bladderCapacityComboBox.getItems().addAll("<200 ml", "200-400 ml", "400-500 ml", ">500 ml");
        otherComboBox.getItems().addAll("None", "Stroke", "Sclerosis");
    }

    public void btnSaveFunc() {

        try {
            String a = bladderCapacityComboBox.getValue().toString();
            int b = Integer.parseInt(IEsTextField.getText());
            int c = Integer.parseInt(UEsTextField.getText());
            int d = Integer.parseInt(urinationTextField.getText());
            int e = Integer.parseInt(nocturiaTextfield.getText());
            String f = otherComboBox.getValue().toString();
            DatabaseHandler.saveSymptonsToDb(a, b, c, d, e, f);
            saveTextVerify.setText("Saved!");
        } catch (Exception g) {
            System.out.println("Error btnSaveFunc");
        }
    }

}
