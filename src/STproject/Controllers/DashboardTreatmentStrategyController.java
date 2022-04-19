package STproject.Controllers;

import static STproject.Main.Main.*;
import STproject.Models.DatabaseHandler;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.text.Text;

public class DashboardTreatmentStrategyController implements Initializable {

    @FXML
    private Text textPatientBladderCapacity, textPatientIEsPerDay,
            textPatientUEsPerDay, textPatientUrinationPerDay, textPatientNocturiaEpisodes,
            textPatientOther;

    @FXML // stimulation paradigm checkboxes
    private CheckBox TLContinuous, TL4Hours, TL30Minutes,
            TL15Minutes, TLDeactivated, Urge60Seconds, UrgeDeactivated;

    @FXML // max intensity checkboxes
    private CheckBox TL5, TL4, TL3, TL2, TL1,
            Urge5, Urge4, Urge3, Urge2, Urge1;

    @FXML
    private Button btnSave;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

// DUMMY SYMPTOMS
        symptoms.setBladderCapacity(">500 ml");
        symptoms.setIEsPerDay(5);
        symptoms.setUEsPerDay(9);
        symptoms.setUrinationPerDay(750);
        symptoms.setNocturiaEpisodes(3);
        symptoms.setOther("None");

// opdater symptoms liste
        textPatientBladderCapacity.setText(symptoms.getBladderCapacity());
        textPatientIEsPerDay.setText(Integer.toString(symptoms.getIEsPerDay()));
        textPatientUEsPerDay.setText(Integer.toString(symptoms.getUEsPerDay()));
        textPatientUrinationPerDay.setText(Integer.toString(symptoms.getUrinationPerDay()));
        textPatientNocturiaEpisodes.setText(Integer.toString(symptoms.getNocturiaEpisodes()));
        textPatientOther.setText(symptoms.getOther());

// sæt symptoms visible(true)
        textPatientBladderCapacity.setVisible(true);
        textPatientIEsPerDay.setVisible(true);
        textPatientUEsPerDay.setVisible(true);
        textPatientUrinationPerDay.setVisible(true);
        textPatientNocturiaEpisodes.setVisible(true);
        textPatientOther.setVisible(true);
    }

    public void clickBtnSave() {
        DatabaseHandler.saveTreatmentToDb();
    }

// TIME LIMITED PARADIGM START
    public void clickTLContinuous() {
        TLContinuous.setSelected(true);
        TL4Hours.setSelected(false);
        TL30Minutes.setSelected(false);
        TL15Minutes.setSelected(false);
        TLDeactivated.setSelected(false);
        treatmentSetting.setTimeLimitedSetting("Continuous");

        TL5.setDisable(false);
        TL4.setDisable(false);
        TL3.setDisable(false);
        TL2.setDisable(false);
        TL1.setDisable(false);
    }

    public void clickTL4Hours() {
        TLContinuous.setSelected(false);
        TL4Hours.setSelected(true);
        TL30Minutes.setSelected(false);
        TL15Minutes.setSelected(false);
        TLDeactivated.setSelected(false);
        treatmentSetting.setTimeLimitedSetting("4 hours");

        TL5.setDisable(false);
        TL4.setDisable(false);
        TL3.setDisable(false);
        TL2.setDisable(false);
        TL1.setDisable(false);
    }

    public void clickTL30Minutes() {
        TLContinuous.setSelected(false);
        TL4Hours.setSelected(false);
        TL30Minutes.setSelected(true);
        TL15Minutes.setSelected(false);
        TLDeactivated.setSelected(false);
        treatmentSetting.setTimeLimitedSetting("30 minutes");

        TL5.setDisable(false);
        TL4.setDisable(false);
        TL3.setDisable(false);
        TL2.setDisable(false);
        TL1.setDisable(false);
    }

    public void clickTL15Minutes() {
        TLContinuous.setSelected(false);
        TL4Hours.setSelected(false);
        TL30Minutes.setSelected(false);
        TL15Minutes.setSelected(true);
        TLDeactivated.setSelected(false);
        treatmentSetting.setTimeLimitedSetting("15 minutes");

        TL5.setDisable(false);
        TL4.setDisable(false);
        TL3.setDisable(false);
        TL2.setDisable(false);
        TL1.setDisable(false);
    }

    public void clickTLDeactivated() {
        TLContinuous.setSelected(false);
        TL4Hours.setSelected(false);
        TL30Minutes.setSelected(false);
        TL15Minutes.setSelected(false);
        TLDeactivated.setSelected(true);

        TL5.setSelected(false);
        TL5.setDisable(true);
        TL4.setSelected(false);
        TL4.setDisable(true);
        TL3.setSelected(false);
        TL3.setDisable(true);
        TL2.setSelected(false);
        TL2.setDisable(true);
        TL1.setSelected(false);
        TL1.setDisable(true);
        treatmentSetting.setTimeLimitedIntensity(0);
        treatmentSetting.setTimeLimitedSetting("Deactivated");
    }
// TIME LIMITED PARADIGM END

// URGE PARADIGM START
    public void clickUrge60Seconds() {
        Urge60Seconds.setSelected(true);
        UrgeDeactivated.setSelected(false);
        treatmentSetting.setUrgeSetting("60 seconds");

        Urge5.setDisable(false);
        Urge4.setDisable(false);
        Urge3.setDisable(false);
        Urge2.setDisable(false);
        Urge1.setDisable(false);
    }

    public void clickUrgeDeactivated() {
        Urge60Seconds.setSelected(false);
        UrgeDeactivated.setSelected(true);
        treatmentSetting.setUrgeSetting("Deactivated");

        Urge5.setDisable(true);
        Urge4.setDisable(true);
        Urge3.setDisable(true);
        Urge2.setDisable(true);
        Urge1.setDisable(true);
        treatmentSetting.setUrgeIntensity(0);
    }
// URGE PARADIGM START

// TIME LIMITED MAX INTENSITY START
    public void clickTL5() {
        TL5.setSelected(true);
        TL4.setSelected(false);
        TL3.setSelected(false);
        TL2.setSelected(false);
        TL1.setSelected(false);
        treatmentSetting.setTimeLimitedIntensity(5);
    }

    public void clickTL4() {
        TL5.setSelected(false);
        TL4.setSelected(true);
        TL3.setSelected(false);
        TL2.setSelected(false);
        TL1.setSelected(false);
        treatmentSetting.setTimeLimitedIntensity(4);
    }

    public void clickTL3() {
        TL5.setSelected(false);
        TL4.setSelected(false);
        TL3.setSelected(true);
        TL2.setSelected(false);
        TL1.setSelected(false);
        treatmentSetting.setTimeLimitedIntensity(3);
    }

    public void clickTL2() {
        TL5.setSelected(false);
        TL4.setSelected(false);
        TL3.setSelected(false);
        TL2.setSelected(true);
        TL1.setSelected(false);
        treatmentSetting.setTimeLimitedIntensity(2);
    }

    public void clickTL1() {
        TL5.setSelected(false);
        TL4.setSelected(false);
        TL3.setSelected(false);
        TL2.setSelected(false);
        TL1.setSelected(true);
        treatmentSetting.setTimeLimitedIntensity(1);
    }
// TIME LIMITED MAX INTENSITY END

// URGE MAX INTENSITY START
    public void clickUrge5() {
        Urge5.setSelected(true);
        Urge4.setSelected(false);
        Urge3.setSelected(false);
        Urge2.setSelected(false);
        Urge1.setSelected(false);
        treatmentSetting.setUrgeIntensity(5);
    }

    public void clickUrge4() {
        Urge5.setSelected(false);
        Urge4.setSelected(true);
        Urge3.setSelected(false);
        Urge2.setSelected(false);
        Urge1.setSelected(false);
        treatmentSetting.setUrgeIntensity(4);
    }

    public void clickUrge3() {
        Urge5.setSelected(false);
        Urge4.setSelected(false);
        Urge3.setSelected(true);
        Urge2.setSelected(false);
        Urge1.setSelected(false);
        treatmentSetting.setUrgeIntensity(3);
    }

    public void clickUrge2() {
        Urge5.setSelected(false);
        Urge4.setSelected(false);
        Urge3.setSelected(false);
        Urge2.setSelected(true);
        Urge1.setSelected(false);
        treatmentSetting.setUrgeIntensity(2);
    }

    public void clickUrge1() {
        Urge5.setSelected(false);
        Urge4.setSelected(false);
        Urge3.setSelected(false);
        Urge2.setSelected(false);
        Urge1.setSelected(true);
        treatmentSetting.setUrgeIntensity(1);
    }
// URGE MAX INTENSITY END
}
