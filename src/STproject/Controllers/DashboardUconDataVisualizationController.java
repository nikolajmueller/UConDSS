package STproject.Controllers;

import java.io.File;
import java.io.FileNotFoundException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.Scanner;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javax.swing.JOptionPane;

public class DashboardUconDataVisualizationController implements Initializable {

    @FXML
    private ListView<String> listView_Urge;

    @FXML
    private ListView<String> listView_TimeLimited;

    @FXML
    private TextField average_Urge;

    @FXML
    private TextField average_TimeLimitedSessions;

    @FXML
    private TextField average_ConstantIntensity;

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
                
                int count_AverageUrge = 0;
                int count_TimelimitedSessions = 0;
                
                for (int i = 0; i < splitted.length; i++) {

                    String trimmedData = splitted[i].substring(18, 75);
                    String trimmedData1 = trimmedData.trim();  // trim() fjerner alle "whitespace" tilsidst i hver String

                    String test = splitted[i].substring(18,110);
                    
                    //if (test.endsWith("s")){
                        System.out.println(test);
                    ///}

                    if (trimmedData1.endsWith("MSG_BUTTON_FUNCTION_RELEASED")) {
                        count_AverageUrge++;
                        listView_Urge.getItems().add(trimmedData1);

                    }

                    if (trimmedData1.endsWith("MSG_LOG_THERAPY_STARTED") || trimmedData1.endsWith("MSG_LOG_THERAPY_STOPPED") || trimmedData1.endsWith("MSG_LOG_THERAPY_INTENSITY_CHANGE")) {
                        count_TimelimitedSessions++;
                        
                        listView_TimeLimited.getItems().add(trimmedData1);

                    }

                }
                average_Urge.setText("" + count_AverageUrge);
                average_TimeLimitedSessions.setText("" + count_TimelimitedSessions / 2);
            }

            inputStream.close();
        } catch (FileNotFoundException e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

}
