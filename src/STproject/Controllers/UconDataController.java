package STproject.Controllers;

import STproject.Main.Main;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.Scanner;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javax.swing.JOptionPane;

public class UconDataController implements Initializable {

    @FXML
    private ListView<String> listView_Urge;

    @FXML
    private ListView<String> listView_TimeLimited;

    @FXML
    private ListView<String> listView_TimeLimited_Intensity;

    @FXML
    private TextField average_Urge;

    @FXML
    private TextField average_TimeLimitedSessions;

    @FXML
    private TextField cpr, name, age, gender;

    @FXML
    private Button btnToSymptoms;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        btnToSymptoms.setDisable(true);

        cpr.setText(Main.patient.getCprNumber());
        cpr.setStyle("-fx-text-fill: White;");
        name.setText(Main.patient.getName());
        name.setStyle("-fx-text-fill: White;");
        age.setText("" + Main.patient.getAge());
        age.setStyle("-fx-text-fill: White;");
        gender.setText(Main.patient.getGender());
        gender.setStyle("-fx-text-fill: White;");

        String fileName = "C:\\jar-files\\files\\log_2021-04-09.txt";
        File file = new File(fileName);

        try {
            Scanner inputStream = new Scanner(file);

            while (inputStream.hasNext()) {

                inputStream.useDelimiter("/");
                String data = inputStream.next();
                String[] splitted = data.split("\n");  // "\n" betyder linje skift og muliggør at man kan gemme hver række i et array

                int count_AverageUrge = 0;
                int count_TimelimitedSessions = 0;

                for (int i = 0; i < splitted.length; i++) {
                    String trimmedData = splitted[i].substring(18, 75);
                    String trimmedData1 = trimmedData.trim();  // trim() fjerner alle "whitespace" tilsidst i hver String
                    String intensityString = splitted[i].substring(43, 110);

                    // Denne bruges til at få urge activations og average urge
                    if (trimmedData1.endsWith("MSG_BUTTON_FUNCTION_RELEASED")) {
                        count_AverageUrge++;
                        listView_Urge.getItems().add(trimmedData1);
                    }
                    // Denne her if sætning bliver brugt til at få average time limited sessions
                    if (trimmedData1.endsWith("MSG_LOG_THERAPY_STOPPED")) {
                        count_TimelimitedSessions++;
                    }
                    // Denne bruges til Tilimited sessions
                    if (trimmedData1.endsWith("MSG_LOG_THERAPY_STARTED")
                            || trimmedData1.endsWith("MSG_LOG_THERAPY_STOPPED")
                            || trimmedData1.endsWith("MSG_LOG_THERAPY_INTENSITY_CHANGE")
                            || intensityString.startsWith("MSG_LOG_THERAPY_INTENSITY_CHANGE")) {

                        String intensityString1 = intensityString.substring(65, 67);

                        listView_TimeLimited.getItems().add(trimmedData1);
                        listView_TimeLimited_Intensity.getItems().add(intensityString1);
                    }
                }
                average_Urge.setText("" + count_AverageUrge);
                average_TimeLimitedSessions.setText("" + count_TimelimitedSessions);
            }
            inputStream.close();
        } catch (FileNotFoundException e) {
            JOptionPane.showMessageDialog(null, e);
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

    @FXML
    void selectNewPatient(ActionEvent event) throws IOException {

        Parent toSearchCreateParent = FXMLLoader.load(getClass().getResource("/ressources/SearchCreateView.fxml"));
        Scene toSearchCreateScene = new Scene(toSearchCreateParent);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(toSearchCreateScene);
        window.centerOnScreen();
        window.show();
    }

    @FXML
    void toSymptomEvaluation(ActionEvent event) throws IOException {

        Parent toSearchCreateParent = FXMLLoader.load(getClass().getResource("/ressources/DashboardSymptomEvaluation.fxml"));
        Scene toSearchCreateScene = new Scene(toSearchCreateParent);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(toSearchCreateScene);
        window.centerOnScreen();
        window.show();
    }

    @FXML
    void toTreatment(ActionEvent event) throws IOException {

        Parent toSearchCreateParent = FXMLLoader.load(getClass().getResource("/ressources/DashboardTreatmentStrategy.fxml"));
        Scene toSearchCreateScene = new Scene(toSearchCreateParent);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(toSearchCreateScene);
        window.centerOnScreen();
        window.show();
    }

    @FXML
    void toEffect(ActionEvent event) throws IOException {

        Parent toSearchCreateParent = FXMLLoader.load(getClass().getResource("/ressources/DashboardEffectivenessScore.fxml"));
        Scene toSearchCreateScene = new Scene(toSearchCreateParent);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(toSearchCreateScene);
        window.centerOnScreen();
        window.show();
    }

}
