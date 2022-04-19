/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXML.java to edit this template
 */
package STproject.Main;

import STproject.Models.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javax.swing.JOptionPane;

public class Main extends Application {

    public static PatientsCprList patient = new PatientsCprList();

    public static void main(String[] args) throws IOException {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/ressources/SearchCreateView.fxml")); // /ressources/LoginView.fxml
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
        stage.setTitle("UConDSS");   // Sætter titel
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
