package STproject.Controllers;

import STproject.Models.DatabaseHandler;
import STproject.Main.Main;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javax.swing.JOptionPane;

public class LoginController implements Initializable {
    
    @FXML
    private AnchorPane pane_login, pane_signup;
    
    @FXML
    private TextField username_lg, username_up, password_up, email_up;
    
    @FXML
    private PasswordField password_lg;
    
    @FXML
    private Button btnLogin;
    
    Connection conn = null;
    ResultSet rs = null;
    PreparedStatement pst = null;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }

    // Muliggør at man kan skifte mellem login og signin
    public void loginPaneShow() {
        pane_login.setVisible(true);
        pane_signup.setVisible(false);
    }
    
    public void signupPaneShow() {
        pane_signup.setVisible(true);
        pane_login.setVisible(false);
    }
    
    @FXML
    void login(ActionEvent event) {
        conn = DatabaseHandler.getConnection(); // get db connection
        String sqlUserLogin = "SELECT * FROM Clinician WHERE username = ? and password = ?";

        // prepare and execute sqlUser query
        try {
            pst = conn.prepareStatement(sqlUserLogin);
            pst.setString(1, username_lg.getText());
            pst.setString(2, password_lg.getText());
            rs = pst.executeQuery();
            
            if (rs.next()) {
                Main.clinician.setId(rs.getInt(1));
                Main.clinician.setUsername(rs.getString(2));
                Main.clinician.setEmail(rs.getString(4));

                // De næste 5 linjer skifter scene
                Parent toDashboardParent = FXMLLoader.load(getClass().getResource("/ressources/SearchCreateView.fxml"));
                Scene toDashboardScene = new Scene(toDashboardParent);
                Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
                window.setScene(toDashboardScene);
                window.centerOnScreen();
                window.show();
                window.setTitle("UConDSS - " + Main.clinician.getId() + " : " + Main.clinician.getUsername());
            } else {
                JOptionPane.showMessageDialog(null, "Invalid Username or Password");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }
    
    @FXML // SKAL UNDER DATABASEHANDLER!
    void addUsers(ActionEvent event) throws SQLException {
        
        if (username_up.getText().length() > 2 && password_up.getText().length() > 2) {
            conn = DatabaseHandler.getConnection(); // get db connection

            try {
                // sqlUser query to create user
                String sqlUser = "INSERT INTO Clinician (username,password,email) values (?,?,?)";

                // sqlUser query to check for duplicates in database (username OR email)
                String sqlUserDupe = "SELECT COUNT(*) FROM Clinician WHERE username LIKE \"" + username_up.getText() + "\""
                        + "        OR email LIKE \"" + email_up.getText() + "\"";
                
                Statement stDupe = conn.createStatement();
                ResultSet userRes = stDupe.executeQuery(sqlUserDupe);
                userRes.next();
                String dupeCountrow = userRes.getString(1);

                // if no duplicates -> prepare sqlUser statement
                if (dupeCountrow.equals("0")) {
                    pst = conn.prepareStatement(sqlUser);
                    pst.setString(1, username_up.getText());
                    pst.setString(2, password_up.getText());
                    pst.setString(3, email_up.getText());
                    pst.execute();

                    // if user is registered succesfully -> show login page
                    JOptionPane.showMessageDialog(null, "User registered");
                    pane_login.setVisible(true);
                    pane_signup.setVisible(false);
                } else {
                    JOptionPane.showMessageDialog(null, "Username or email already registered");
                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, e);
            }
        } else {
            JOptionPane.showMessageDialog(null, "Username or password need to be at least 3 characters");
        }
    }
    
}
