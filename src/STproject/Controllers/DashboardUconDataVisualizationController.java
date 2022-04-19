/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package STproject.Controllers;

import java.io.File;
import java.io.FileNotFoundException;
import static java.lang.Math.log;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.Scanner;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author mathi
 */
public class DashboardUconDataVisualizationController implements Initializable {

    @FXML
    private ListView<String> listView_Urge;
    
    @FXML
    private ListView<String> listView_TimeLimited;
    
    @FXML
    private TextField average_Urge;
    
    
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        String fileName = "C:\\jar-files\\files\\log_2021-04-09.txt";
        File file = new File(fileName);

        try {
            Scanner inputStream = new Scanner(file);

            while (inputStream.hasNext()) {

                inputStream.useDelimiter("/");

                String data = inputStream.next();

                String[] splitted = data.split("\n");  // "\n" betyder linje skift og muliggør at man kan gemme hver række i et array
                
                for (int i = 0; i < splitted.length; i++) {

                    String trimmedData = splitted[i].substring(18, 75);
                    String trimmedData1 = trimmedData.trim();  // trim() fjerner alle "whitespace" tilsidst i hver String

                    if (trimmedData1.endsWith("MSG_BUTTON_FUNCTION_RELEASED")) {
                        
                        listView_Urge.getItems().add(trimmedData1);
                        
                        average_Urge.setText("" + trimmedData1.length());      // Den her tager det forkerte gennemsnit lige nu
                    }
                }
            }

            inputStream.close();
        } catch (FileNotFoundException e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }


}
