
package STproject.Controllers;

import STproject.Models.*;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

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



}
