package STproject.Controllers;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

public class DashboardSymptomEvaluationController implements Initializable {

    @FXML
    private ComboBox bladderCapacityComboBox, otherComboBox;

    @FXML
    private TextField IEsTextField, UEsTextField,
            urinationTextField, nocturiaTextfield;

    @FXML
    private Button btnSave;

    /**
     * Initializes the controller class
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        String patientName = "John Doe"; // dummy
        String patientCPR = "040875-1337"; // dummy

        bladderCapacityComboBox.getItems().addAll("<200 ml", "200-400 ml", "400-500 ml", ">500 ml");
        otherComboBox.getItems().addAll("Stroke", "Sclerosis");
    }

}
