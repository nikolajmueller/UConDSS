/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package STproject.Models;

import STproject.Main.Main;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javax.swing.JOptionPane;

public class DatabaseHandler {

    public static ObservableList<PatientsCprList> ob = FXCollections.observableArrayList();

    public static Connection getConnection() {

        try {
            // Your database    // db.course.hst.aau.dk = localhost
            String url = "jdbc:mysql://db.course.hst.aau.dk/hst_2022st6405"; // where jdbc is the API, mysql is the database, localhost is the server name on which mysql is running, we may also use IP address, 3306 is the port number and sonoo is the database name. We may use any database, in such case, we need to replace the sonoo with our database name.
            String user = "hst_2022st6405";
            String password = "eezahgoukaiheguphiej";

            Class.forName("com.mysql.jdbc.Driver");      // Driver navn     com.mysql.jdbc.Driver
            Connection conn = DriverManager.getConnection(url, user, password);

            return conn;
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(DatabaseHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;

    }

    public static PatientsCprList readPatient() {

        try {
            String sqlQuery = "select * from Patient";
            ResultSet rs_patient = getConnection().createStatement().executeQuery(sqlQuery);

            while (rs_patient.next()) {
                ob.add(new PatientsCprList(rs_patient.getString("cprNumber")));
            }
        } catch (SQLException e) {
            System.err.println("Cannot connect to database server");
        }
        return null;
    }

    public static void saveSymptonsToDb(String patientCPR, String a, int b, int c, int d, int e, String f) {
        try {
            Connection conn = DatabaseHandler.getConnection();

// check om CPR allerede har registeret baseline symptoms
            PreparedStatement psCheckIfExists = conn.prepareStatement("SELECT COUNT(*) FROM SymptomsBaseline AS countrow WHERE patientCPR = ?");
            psCheckIfExists.setString(1, Main.patient.getCprNumber());
            ResultSet rsIfExists = psCheckIfExists.executeQuery();
            rsIfExists.next();
            int countrow = rsIfExists.getInt(1);

// hvis NOT EXISTS, gem symptomer til DB
            if (countrow == 0) {
                PreparedStatement ps = conn.prepareStatement(""
                        + "INSERT INTO SymptomsBaseline"
                        + " (patientCPR, bladderCapacity, IEsPerDay,"
                        + " UEsPerDay, UrinationPerDay, NocturiaEpisodes, Other)"
                        + " VALUES (?,?,?,?,?,?,?)");
                ps.setString(1, Main.patient.getCprNumber());
                ps.setString(2, a);
                ps.setInt(3, b);
                ps.setInt(4, c);
                ps.setInt(5, d);
                ps.setInt(6, e);
                ps.setString(7, f);
                ps.execute();
                conn.close();

// hvis DO EXISTS, send fejlmeddelelse
            } else {
                JOptionPane.showMessageDialog(null, "PATIENT IS ALREADY REGISTERED WITH BASELINE SYMPTOMS");
                //System.out.println("PATIENT ALREADY REGISTERED BASELINE SYMPTOMS");
            }
        } catch (SQLException p) {
            System.err.println("Cannot connect to database server");
        }
    }
}
