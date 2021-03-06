package STproject.Controllers;

import STproject.Main.Main;
import static STproject.Main.Main.patient;
import STproject.Models.DatabaseHandler;
import static STproject.Models.DatabaseHandler.ob;
import STproject.Models.Patient;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
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

public class PatientSearchCreateController implements Initializable {

    @FXML
    private TextField keywordTextField;

    @FXML
    private TableView<Patient> tableView_CPR;

    @FXML
    private TableColumn<Patient, String> col_CPR;

    @FXML
    private TextField field_cpr, field_name;

    @FXML
    private Button btnToDashboard;

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        ob.clear();

        DatabaseHandler.readPatient();
        ObservableList<Patient> ob = DatabaseHandler.ob;

        // set Cell value factory and property value factory
        col_CPR.setCellValueFactory(new PropertyValueFactory<>("cprNumber"));
        // set observableList
        tableView_CPR.setItems(ob);

        //Initial filtered list
        FilteredList<Patient> filteredData = new FilteredList<>(ob, b -> true);

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

        SortedList<Patient> sortedData = new SortedList<>(filteredData);

        // Bind sorted result with TableView
        sortedData.comparatorProperty().bind(tableView_CPR.comparatorProperty());

        tableView_CPR.setItems(sortedData);

// Sikre at der kun kan indtastes tal i CPR felt
        keywordTextField.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (!newValue.matches("\\d*")) {
                    keywordTextField.setText(newValue.replaceAll("[^\\d]", ""));
                }
            }
        });

// sikre at der ikke kommer mere end 10 tal i CPR felt
        keywordTextField.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (keywordTextField.getText().length() > 10) {
                    String s = keywordTextField.getText().substring(0, 10);
                    keywordTextField.setText(s);
                }
            }
        });

// Sikre at der kun kan indtastes tal i CPR felt
        field_cpr.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (!newValue.matches("\\d*")) {
                    field_cpr.setText(newValue.replaceAll("[^\\d]", ""));
                }
            }
        });

// sikre at der ikke kommer mere end 10 tal i CPR felt
        field_cpr.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (field_cpr.getText().length() > 10) {
                    String s = field_cpr.getText().substring(0, 10);
                    field_cpr.setText(s);
                }
            }
        });

    }

    // select row in tableView_patientID and display selected value in txtField_series
    public void displaySelectedCprNumber(MouseEvent event) {

        Main.patient = tableView_CPR.getSelectionModel().getSelectedItem();

        if (Main.patient == null) {

        } else {
            btnToDashboard.setDisable(false);
        }

    }

    public void btnToDashboard(ActionEvent event) throws IOException {
        int patientStatus = DatabaseHandler.checkPatientStatus();

        if (patientStatus == 0) {
            Parent toDashboardParent = FXMLLoader.load(getClass().getResource("/ressources/DashboardSymptomEvaluation.fxml"));
            Scene toDashboardScene = new Scene(toDashboardParent);
            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
            window.setScene(toDashboardScene);
            window.centerOnScreen();
            window.show();
        } else if (patientStatus == 1) {
            DatabaseHandler.readSymptoms();
            Parent toDashboardParent = FXMLLoader.load(getClass().getResource("/ressources/DashboardTreatmentStrategy.fxml"));
            Scene toDashboardScene = new Scene(toDashboardParent);
            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
            window.setScene(toDashboardScene);
            window.centerOnScreen();
            window.show();
        } else if (patientStatus == 2) {
            DatabaseHandler.readSymptoms();
            DatabaseHandler.readLatestTreatment();
            Parent toDashboardParent = FXMLLoader.load(getClass().getResource("/ressources/DashboardEffectivenessScore.fxml"));
            Scene toDashboardScene = new Scene(toDashboardParent);
            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
            window.setScene(toDashboardScene);
            window.centerOnScreen();
            window.show();
        }
    }

    Connection conn = null;

    // CREATE PATIENT
    public void btnSavePatientFunc(ActionEvent event) {      //funktion til knappen save patient
        try {  // gemmer v??rdier i @FXML-boksene i patient
            patient.setCprNumber(field_cpr.getText());
            patient.setName(field_name.getText());
            patient.setAge(calculateAge(patient.getCprNumber()));
            determineGender();

            conn = DatabaseHandler.getConnection(); // get db connection
            // sqlUser query to check for duplicates in database (CPR)
            String sqlUserDupe = "SELECT COUNT(*) FROM PatientList where CPR LIKE \"" + field_cpr.getText() + "\"";

            Statement stDupe = conn.createStatement();
            ResultSet userRes = stDupe.executeQuery(sqlUserDupe);
            userRes.next();
            String dupeCountrow = userRes.getString(1);

            if (!field_cpr.getText().matches("\\d{10}")) {
                JOptionPane.showMessageDialog(null, "Invalid CPR number");
            } else if (field_name.getText().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Fill in name");
            } else {
                if (patient.getAge() != 0 && dupeCountrow.equals("0")) {
                    DatabaseHandler.savePatientToDb(patient.getCprNumber(),
                            patient.getName(),
                            patient.getAge(),
                            patient.getGender());
                    Parent toDashboardParent = FXMLLoader.load(getClass().getResource("/ressources/DashboardSymptomEvaluation.fxml"));
                    Scene toDashboardScene = new Scene(toDashboardParent);
                    Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
                    window.setScene(toDashboardScene);
                    window.centerOnScreen();
                    window.show();
                    JOptionPane.showMessageDialog(null, "Patient registered");
                } else {
                    if (dupeCountrow.equals("1")) {
                        JOptionPane.showMessageDialog(null, "Patient already registered");
                    } else {
                        JOptionPane.showMessageDialog(null, "Invalid CPR");
                    }
                }
            }
        } catch (Exception f) {
            JOptionPane.showMessageDialog(null, "Fill in valid CPR number");
        }
    }

    public int calculateAge(String cpr) {   // Funktion til at beregne alder
        int patientAge = 0;
        try {
            String CPRInput = cpr.substring(4, 6);
            int CPR = Integer.parseInt(CPRInput);
            int patientBirthYear = CPR;
            LocalDate currentDate = LocalDate.now();
            int currentYear = (currentDate.getYear() - 2000);

// find birthYear (MAX 100 ??r)
            if (CPR <= currentYear) {
                patientBirthYear = CPR + 2000;
            } else if (CPR > currentYear) {
                patientBirthYear = CPR + 1900;
            }

// FORMAT yyyy-MM-dd:  eksempel 1964-02-05
            String patientBirthYearStr = patientBirthYear
                    + "-" + cpr.substring(2, 4)
                    + "-" + cpr.substring(0, 2);

            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            LocalDate dateTime = LocalDate.parse(patientBirthYearStr, dtf);

            patientAge = Period.between(dateTime, currentDate).getYears();
        } catch (Exception e) {
            //JOptionPane.showMessageDialog(null, "Invalid CPR number.");
        }
        return patientAge;
    }

// funktion til at bestemme k??n ud fra sidste cifer i CPR
    public void determineGender() {
        if (Integer.parseInt(patient.getCprNumber().substring(9, 10)) % 2 == 0) {
            patient.setGender("Female");
        } else {
            patient.setGender("Male");
        }
    }

    @FXML
    void logout(ActionEvent event) throws IOException {

        Parent toLoginParent = FXMLLoader.load(getClass().getResource("/ressources/LoginView.fxml"));
        Scene toLoginScene = new Scene(toLoginParent);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(toLoginScene);
        window.centerOnScreen();
        window.setTitle("UCon");
        window.show();
    }

}
