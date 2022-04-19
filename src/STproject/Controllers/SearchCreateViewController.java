package STproject.Controllers;

import STproject.Main.Main;
import STproject.Models.DatabaseHandler;
import STproject.Models.PatientsCprList;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Observable;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class SearchCreateViewController implements Initializable {

    @FXML
    private TextField keywordTextField;

    @FXML
    private TableView<PatientsCprList> tableView_CPR;

    @FXML
    private TableColumn<PatientsCprList, String> col_CPR;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        DatabaseHandler.readPatient();
        ObservableList<PatientsCprList> ob = DatabaseHandler.ob;

        // set Cell value factory and property value factory
        col_CPR.setCellValueFactory(new PropertyValueFactory<>("cprNumber"));
        // set observableList
        tableView_CPR.setItems(ob);

        //Initial filtered list
        FilteredList<PatientsCprList> filteredData = new FilteredList<>(ob, b -> true);

        keywordTextField.textProperty().addListener((Observable, oldValue, newValue) -> {
            filteredData.setPredicate(PatientsCprList -> {
                // If no search value then display all records or whatever records it current have. No change
                if (newValue.isEmpty() || newValue == null) {  // den gider ikke:   || newValue.isBlank()
                    return true;
                }

                String searchKeyword = newValue.toLowerCase();

                if (PatientsCprList.getCprNumber().toLowerCase().indexOf(searchKeyword) > -1) {
                    return true; // Means we found a match in CPR
                } else {
                    return false; // No match found
                }
            });
        });

        SortedList<PatientsCprList> sortedData = new SortedList<>(filteredData);

        // Bind sorted result with TableView
        sortedData.comparatorProperty().bind(tableView_CPR.comparatorProperty());

        tableView_CPR.setItems(sortedData);

    }

    // select row in tableView_patientID and display selected value in txtField_series
    @FXML
    public void displaySelectedCprNumber(MouseEvent event) {

        Main.patient = tableView_CPR.getSelectionModel().getSelectedItem();

        if (Main.patient == null) {

        } else {
            keywordTextField.setText(Main.patient.getCprNumber());
        }
        
    }
    
    @FXML
    public void btnToDashboard(ActionEvent event) throws IOException {
        Parent toDashboardParent = FXMLLoader.load(getClass().getResource("/ressources/DashboardSymptomEvaluation.fxml"));
        Scene toDashboardScene = new Scene(toDashboardParent);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(toDashboardScene);
        window.centerOnScreen();
        window.show();
    }

}
