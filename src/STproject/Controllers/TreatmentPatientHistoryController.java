
package STproject.Controllers;

import STproject.Models.DatabaseHandler;
import static STproject.Models.DatabaseHandler.ob_treatment;
import STproject.Models.TreatmentSetting;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class TreatmentPatientHistoryController implements Initializable {

    @FXML
    private TableView<TreatmentSetting> tableView_Treatment;

    @FXML
    private TableColumn<TreatmentSetting, Integer> col_treatmentNumber;

    @FXML
    private TableColumn<TreatmentSetting, String> col_timeLimitedSetting;

    @FXML
    private TableColumn<TreatmentSetting, Integer> col_timeLimitedIntensity;

    @FXML
    private TableColumn<TreatmentSetting, String> col_urgeSetting;

    @FXML
    private TableColumn<TreatmentSetting, Integer> col_urgeIntensity;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        ob_treatment.clear();
        
        DatabaseHandler.readTreatmentSetting();
        ObservableList<TreatmentSetting> ob_treatment = DatabaseHandler.ob_treatment;
        
        // set Cell value factory and property value factory
        col_treatmentNumber.setCellValueFactory(new PropertyValueFactory<>("treatmentNumber"));
        col_timeLimitedSetting.setCellValueFactory(new PropertyValueFactory<>("timeLimitedSetting"));
        col_timeLimitedIntensity.setCellValueFactory(new PropertyValueFactory<>("timeLimitedIntensity"));
        col_urgeSetting.setCellValueFactory(new PropertyValueFactory<>("urgeSetting"));
        col_urgeIntensity.setCellValueFactory(new PropertyValueFactory<>("urgeIntensity"));
        // set observableList
        tableView_Treatment.setItems(ob_treatment);

    }    
    
}
