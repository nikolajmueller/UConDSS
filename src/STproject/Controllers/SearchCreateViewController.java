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
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javax.swing.JOptionPane;

public class SearchCreateViewController implements Initializable {

    @FXML
    private TextField keywordTextField;

    @FXML
    private TableView<PatientsCprList> tableView_CPR;

    @FXML
    private TableColumn<PatientsCprList, String> col_CPR;

    @FXML
    private CheckBox checkBox_Male, checkBox_Female;

    @FXML
    private Button btnSavePatient, button_logout;

    @FXML
    private TextField field_cpr, field_name, field_age;

    @FXML
    private Label label_toSavePatientFillInBlancFields;

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

    // CREATE PATIENT
    @FXML
    public void btnSavePatientFunc(String cprNumber) {      //funktion til knappen save patient
        try {  // gemmer værdier i @FXML-boksene i patient
            // DatabaseHandler.savePatientToDb("181250007", "Name", 0, "Gender"); // Bruges kun til at tjekke forbindelsen til DB
            Main.patient.setCprNumber(field_cpr.getText());
            Main.patient.setName(field_name.getText());
            // Main.patient.setAge(Integer.parseInt(field_age.getText()));
            // Main.patient.setAge(calculateAge(Integer.parseInt(field_cpr.getText()));    // undersøg hvordan den kan regne alder - evt. ny funk.
            //Main.patient.setAge(convertCPRtoAge(field_cpr.setText(patientAge)));

            // if-else statment sørger for, at der kun kan vælges enten Male eller Female
            if (checkBox_Male.isSelected()) {
                Main.patient.setGender("Male");
            } else if (checkBox_Female.isSelected()) {
                Main.patient.setGender("Female");
            }
            /*
            if (field_cpr.getText().isEmpty() == false
                    && field_name.getText().isEmpty() == false
                    && cprNumber.matches("\\d{10}") == true) {
                        Main.patient.setCprNumber(Integer.parseInt(field_cpr.getText()));
                    }
            else if (cprNumber.matches("\\d{10}") == false) {
                label_toSavePatientFillInBlancFields.setText("Incorrect CPR");
            }
            /* if (validateCprLength = true) {

                    } else {
                        validateCprLength = false;
                        label_toSavePatientFillInBlancFields.setText("Incorrect CPR");
            } else {
                label_toSavePatientFillInBlancFields.setText("Fill in blanc fields");
            } */

            DatabaseHandler.savePatientToDb(Main.patient.getCprNumber(),
                    Main.patient.getName(),
                    Main.patient.getAge(),
                    Main.patient.getGender());

        } catch (Exception f) {
            JOptionPane.showMessageDialog(null, f);
        }
    }

    public void checkBoxGenderMale() {    // vælger køn = male vha. checkBox
        if (checkBox_Male.isSelected()) {
            checkBox_Female.setSelected(false);
        }
    }

    public void checkBoxGenderFemale() {    // vælger køn = female vha. checkBox
        if (checkBox_Female.isSelected()) {
            checkBox_Male.setSelected(false);
        }
    }

}
