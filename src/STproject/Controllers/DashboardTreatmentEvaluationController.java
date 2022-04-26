package STproject.Controllers;

import STproject.Main.Main;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;

public class DashboardTreatmentEvaluationController implements Initializable {

    @FXML
    private TextField textField_TL_MaxIntensity;

    @FXML
    private TextField textField_TL_SessionTime;

    @FXML
    private TextField textField_Urge_MaxIntensity;

    @FXML
    private TextField textField_Urge_SessionTime;

    @FXML
    private RadioButton radioBtn_UsedCorrect;

    @FXML
    private RadioButton radioBtn_UsedIncorrect;

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        // Opdater symptoms liste
        textField_TL_SessionTime.setText(Main.treatmentSetting.getTimeLimitedSetting());
        textField_TL_MaxIntensity.setText("" + Main.treatmentSetting.getTimeLimitedIntensity());
        textField_Urge_SessionTime.setText(Main.treatmentSetting.getUrgeSetting());
        textField_Urge_MaxIntensity.setText("" + Main.treatmentSetting.getUrgeIntensity());
    }

    public void clickRadioBtnCorret(ActionEvent event) {
        radioBtn_UsedIncorrect.setSelected(false);
        radioBtn_UsedCorrect.setSelected(true);
    }
    
    public void clickRadioBtnIncorret(ActionEvent event) {
        radioBtn_UsedCorrect.setSelected(false);
        radioBtn_UsedIncorrect.setSelected(true);
    }

}
