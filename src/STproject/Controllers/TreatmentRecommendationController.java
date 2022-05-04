package STproject.Controllers;

import STproject.Models.DatabaseHandler;
import static STproject.Models.DatabaseHandler.ob_treatmentRecommendation;
import STproject.Models.TreatmentSetting;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class TreatmentRecommendationController implements Initializable {

    @FXML
    private TableView<TreatmentSetting> tableView_Treatment;

    @FXML
    private TableColumn<TreatmentSetting, String> col_timeLimitedSetting;

    @FXML
    private TableColumn<TreatmentSetting, Integer> col_timeLimitedIntensity;

    @FXML
    private TableColumn<TreatmentSetting, String> col_urgeSetting;

    @FXML
    private TableColumn<TreatmentSetting, Integer> col_urgeIntensity;
    
    @FXML
    private TableColumn<TreatmentSetting, Integer> col_effect;


    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO

        ob_treatmentRecommendation.clear();
        
        DatabaseHandler.readTreatmentRecommendation();
        ObservableList<TreatmentSetting> ob_treatmentRecommendation = DatabaseHandler.ob_treatmentRecommendation;

        
        // set Cell value factory and property value factory
        col_timeLimitedSetting.setCellValueFactory(new PropertyValueFactory<>("timeLimitedSetting"));
        col_timeLimitedIntensity.setCellValueFactory(new PropertyValueFactory<>("timeLimitedIntensity"));
        col_urgeSetting.setCellValueFactory(new PropertyValueFactory<>("urgeSetting"));
        col_urgeIntensity.setCellValueFactory(new PropertyValueFactory<>("urgeIntensity"));
        col_effect.setCellValueFactory(new PropertyValueFactory<>("overallEffectivenessScore"));

        // set observableList
        tableView_Treatment.setItems(ob_treatmentRecommendation);
        
         
    }

}
