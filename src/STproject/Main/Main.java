/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXML.java to edit this template
 */
package STproject.Main;

import STproject.Models.*;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    public static Patient patient = new Patient();
    public static Symptoms symptoms = new Symptoms();
    public static TreatmentSetting treatmentSetting = new TreatmentSetting();
    public static SymptomEffect symptomEffect = new SymptomEffect();

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/ressources/DashboardEffectivenessScore.fxml")); // /ressources/LoginView.fxml
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
        stage.setTitle("UConDSS");   // sætter titel
        stage.setResizable(false);   // Denne linje gør at man ikke kan udvide skærm manuelt

    }

    public void readClasses() {
        try {
            //Main.patient = DatabaseHandler.readPatient();

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

}
