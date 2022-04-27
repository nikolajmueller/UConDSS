package STproject.Controllers;

import static STproject.Main.Main.*;
import STproject.Models.DatabaseHandler;
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
import javafx.stage.Stage;
import javax.swing.JOptionPane;

public class DashboardTreatmentStrategyController implements Initializable {

    @FXML
    private Label lbPatientBladderCapacity, lbPatientIEsPerDay,
            lbPatientUEsPerDay, lbPatientUrinationPerDay, lbPatientNocturiaEpisodes,
            lbPatientOther;

    @FXML // stimulation paradigm checkboxes
    private CheckBox TLContinuous, TL4Hours, TL30Minutes,
            TL15Minutes, TLDeactivated, Urge60Seconds, UrgeDeactivated;
    
    @FXML
    private Label treatmentSaved;

    @FXML // max intensity checkboxes
    private CheckBox TL5, TL4, TL3, TL2, TL1,
            Urge5, Urge4, Urge3, Urge2, Urge1;

    @FXML
    private Button btnSave, btnExpandGraph;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

// opdater symptoms liste
        lbPatientBladderCapacity.setText(symptoms.getBladderCapacity());
        lbPatientIEsPerDay.setText(Integer.toString(symptoms.getIEsPerDay()));
        lbPatientUEsPerDay.setText(Integer.toString(symptoms.getUEsPerDay()));
        lbPatientUrinationPerDay.setText(Integer.toString(symptoms.getUrinationPerDay()));
        lbPatientNocturiaEpisodes.setText(Integer.toString(symptoms.getNocturiaEpisodes()));
        lbPatientOther.setText(symptoms.getOther());

// sæt symptoms visible(true)
    }

    public void clickBtnSave(ActionEvent event) {
        int emptyField = checkForEmptyField();

        if (emptyField == 0) {
            try {
                DatabaseHandler.saveTreatmentToDb();
                treatmentSaved.setText("Treatment saved");
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Error connection to database");
            }
        }
    }

    public int checkForEmptyField() {
        int emptyField = 0;

        if (!TLContinuous.isSelected() && !TL4Hours.isSelected() && !TL30Minutes.isSelected() // TL paradigm
                && !TL15Minutes.isSelected() && !TLDeactivated.isSelected()) {
            emptyField = 1;
        } else if (!TLDeactivated.isSelected() && (!TL5.isSelected() && !TL4.isSelected() // TL intensity
                && !TL3.isSelected() && !TL2.isSelected() && !TL1.isSelected())) {
            emptyField = 1;
        } else if (!Urge60Seconds.isSelected() && !UrgeDeactivated.isSelected()) { // urge paradigm
            emptyField = 1;
        } else if (!UrgeDeactivated.isSelected() && (!Urge5.isSelected() && !Urge4.isSelected() // urge intensity
                && !Urge3.isSelected() && !Urge2.isSelected() && !Urge1.isSelected())) {
            emptyField = 1;
        }
        return emptyField;
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

        Urge5.setSelected(false);
        Urge4.setSelected(false);
        Urge3.setSelected(false);
        Urge2.setSelected(false);
        Urge1.setSelected(false);
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
