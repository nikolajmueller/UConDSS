/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package STproject.Controllers;

import STproject.Models.DatabaseHandler;
import static STproject.Models.DatabaseHandler.ob_treatment;
import STproject.Models.TreatmentSetting;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class TreatmentPatientHistorikController implements Initializable {

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
