package STproject.Main;

import STproject.Models.*;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    public static Clinician clinician = new Clinician();
    public static Patient patient = new Patient();
    public static Symptom symptoms = new Symptom();
    public static TreatmentSetting treatmentSetting = new TreatmentSetting();
    public static SymptomEffect symptomEffect = new SymptomEffect();

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/ressources/LoginView.fxml")); // /ressources/LoginView.fxml
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
        stage.setTitle("UConDSS");   // sætter titel
        stage.setResizable(false);   // Denne linje gør at man ikke kan udvide skærm manuelt
    }

}
