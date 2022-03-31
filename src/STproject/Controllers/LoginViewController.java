package STproject.Controllers;

import STproject.Models.*;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

public class LoginViewController implements Initializable {

    @FXML
    private TableView<PatientsCprList> table_Patient;
    @FXML
    private TableColumn<PatientsCprList, String> col_cprNumber;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        //try {
        //    conn = DatabaseHandler.getConnection();
        // execute sql queryCSV: patient count
        //    ResultSet rs = conn.createStatement().executeQuery("select * from Patient"); 
        //    while (rs.next()) {
        //       ob.add(new PatientsCprList(rs.getString("cprNumber")));
        //     }
        // } catch (SQLException ex) {
        // Logger.getLogger(LoginViewController.class.getName()).log(Level.SEVERE,null,ex);
        // }
        DatabaseHandler.readPatient();
        ObservableList<PatientsCprList> ob = DatabaseHandler.ob;

        // set cell value factory and property value factory        
        col_cprNumber.setCellValueFactory(new PropertyValueFactory<>("cprNumber"));

        // set observableLists
        table_Patient.setItems(ob);

    }

    @FXML
    public void btnToNewSceneFunc(ActionEvent event) throws IOException {
        Parent toToNewSceneParent = FXMLLoader.load(getClass().getResource("/ressources/DashboardMainView.fxml"));
        Scene toToNewSceneScene = new Scene(toToNewSceneParent);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(toToNewSceneScene);
        window.show();
        window.setX(300);    // De her to linjer gor at næste scene ikke starter uden for windows skærmen
        window.setY(100);
        
    }
    /*
    public void symptomsPaneShow() {
        .setVisible(true);
        .setVisible(false);
        .setVisible(false);
        .setVisible(false);
    }

    public void treatmentPaneShow() {
        .setVisible(true);
        .setVisible(false);
        .setVisible(false);
        .setVisible(false);
    }
    
    public void effectPaneShow() {
        .setVisible(true);
        .setVisible(false);
        .setVisible(false);
        .setVisible(false);
    }
    
    public void evaluationPaneShow() {
        .setVisible(true);
        .setVisible(false);
        .setVisible(false);
        .setVisible(false);
    }
*/
}
