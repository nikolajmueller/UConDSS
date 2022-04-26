package STproject.Controllers;

import STproject.Main.Main;
import java.awt.Color;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class DashboardMainViewController implements Initializable {


    @FXML
    private TextField cpr;

    @FXML
    private TextField name;

    @FXML
    private TextField age;

    @FXML
    private TextField gender;
    
    @FXML
    private AnchorPane anchorpane_Evaluation;

    @FXML
    private AnchorPane anchorpane_Effect;

    @FXML
    private AnchorPane anchorpane_Treatment;

    @FXML
    private AnchorPane anchorpane_Symptoms;

    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        cpr.setText(Main.patient.getCprNumber());
        cpr.setStyle("-fx-text-fill: White;");
        name.setText(Main.patient.getName());
        name.setStyle("-fx-text-fill: White;");
        age.setText(""+Main.patient.getAge());
        age.setStyle("-fx-text-fill: White;");
        gender.setText(Main.patient.getGender());
        gender.setStyle("-fx-text-fill: White;");

    }

    public void symptomsShow() {
        anchorpane_Symptoms.setVisible(true);
        anchorpane_Treatment.setVisible(false);
        anchorpane_Effect.setVisible(false);
        anchorpane_Evaluation.setVisible(false);
    }

    public void treatmentShow() {
        anchorpane_Treatment.setVisible(true);
        anchorpane_Symptoms.setVisible(false);
        anchorpane_Effect.setVisible(false);
        anchorpane_Evaluation.setVisible(false);
    }

    public void effectShow() {
        anchorpane_Effect.setVisible(true);
        anchorpane_Treatment.setVisible(false);
        anchorpane_Symptoms.setVisible(false);
        anchorpane_Evaluation.setVisible(false);
    }

    public void evaluationShow() {
        anchorpane_Evaluation.setVisible(true);
        anchorpane_Symptoms.setVisible(false);
        anchorpane_Treatment.setVisible(false);
        anchorpane_Effect.setVisible(false);
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

    
}
