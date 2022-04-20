/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package STproject.Models;

import STproject.Main.Main;
import static STproject.Main.Main.treatmentSetting;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
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
            String sqlQuery = "select * from TestPatient";
            ResultSet rs_patient = getConnection().createStatement().executeQuery(sqlQuery);

            while (rs_patient.next()) {
                ob.add(new PatientsCprList(rs_patient.getString("cprNumber")));
            }
        } catch (SQLException e) {
            System.err.println("Cannot connect to database server");
        }
        return null;
    }

    public static void savePatientToDb(String CPR, String name, int age, String gender) {
        try {
            // Undersøger om CPR er registreret i DB
            Connection conn = DatabaseHandler.getConnection();  // forbinder til Db-handler med navnet conn
            PreparedStatement psCheckIfExists = conn.prepareStatement("SELECT COUNT(*) FROM PatientList AS countrow WHERE CPR = ?");
            psCheckIfExists.setString(1, CPR);
            ResultSet rsIfExists = psCheckIfExists.executeQuery();
            rsIfExists.next();
            int countToRow = rsIfExists.getInt(1);

            // Hvis CPR ikke er registreret, gemmes patienten i BD
            if (countToRow == 0) {
                PreparedStatement psCreatePatient = conn.prepareStatement("INSERT INTO PatientList"
                        + " (CPR, Name, Age, Gender)"
                        + " VALUES (?, ?, ?, ?)");
                psCreatePatient.setString(1, CPR);
                psCreatePatient.setString(2, name);
                psCreatePatient.setInt(3, age);
                psCreatePatient.setString(4, gender);
                psCreatePatient.execute();
                conn.close();     // lukker connection til DB
            } // Hvis CPR er registreret, vises fejlmeddelelse
            else {
                System.out.println("error 123");
                //JOptionPane.showMessageDialog(null, "Patient already registered");
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }

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

// hvis NOT EXISTS (0), gem symptomer til DB
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

// hvis DO EXISTS (1), send fejlmeddelelse
            } else {
                JOptionPane.showMessageDialog(null, "PATIENT IS ALREADY REGISTERED WITH BASELINE SYMPTOMS");
            }
        } catch (SQLException p) {
            System.err.println("Cannot connect to database server");
        }
    }

    public static void saveTreatmentToDb() {
        try {
            Connection conn = DatabaseHandler.getConnection();

            PreparedStatement psCheckIfExists = conn.prepareStatement(
                    "SELECT DISTINCT COUNT(treatmentNumber) FROM patientTreatment WHERE CPR = ?");
            psCheckIfExists.setString(1, Main.patient.getCprNumber());
            ResultSet rsIfExists = psCheckIfExists.executeQuery();
            rsIfExists.next();
            int countrow = rsIfExists.getInt(1);

            String currentDate = LocalDate.now().toString();

            if (countrow == 0) {
                countrow = 1;
                PreparedStatement ps = conn.prepareStatement(""
                        + "INSERT INTO `patientTreatment`(`CPR`, `treatmentNumber`, `timeLimitedSetting`,"
                        + " `timeLimitedIntensity`, `urgeSetting`, `urgeIntensity`, `dateSaved`)"
                        + " VALUES (?,?,?,?,?,?,?)");
                ps.setString(1, Main.patient.getCprNumber());
                ps.setInt(2, countrow);
                ps.setString(3, treatmentSetting.getTimeLimitedSetting());
                ps.setInt(4, treatmentSetting.getTimeLimitedIntensity());
                ps.setString(5, treatmentSetting.getUrgeSetting());
                ps.setInt(6, treatmentSetting.getUrgeIntensity());
                ps.setString(7, currentDate);

                ps.execute();
                conn.close();
            } else {
                PreparedStatement psCheckDate = conn.prepareStatement(
                        "SELECT COUNT(*) FROM patientTreatment WHERE dateSaved = ?");
                psCheckDate.setString(1, currentDate);
                ResultSet dateExistsSQL = psCheckDate.executeQuery();
                dateExistsSQL.next();
                int dateExists = dateExistsSQL.getInt(1);
                if (dateExists == 0) {
                    countrow++;
                    PreparedStatement ps = conn.prepareStatement(""
                            + "INSERT INTO `patientTreatment`(`CPR`, `treatmentNumber`, `timeLimitedSetting`,"
                            + " `timeLimitedIntensity`, `urgeSetting`, `urgeIntensity`)"
                            + " VALUES (?,?,?,?,?,?)");
                    ps.setString(1, Main.patient.getCprNumber());
                    ps.setInt(2, countrow);
                    ps.setString(3, treatmentSetting.getTimeLimitedSetting());
                    ps.setInt(4, treatmentSetting.getTimeLimitedIntensity());
                    ps.setString(5, treatmentSetting.getUrgeSetting());
                    ps.setInt(6, treatmentSetting.getUrgeIntensity());

                    ps.execute();
                    conn.close();
                } else {
                    JOptionPane.showMessageDialog(null, "Patient already has treatment registered with current date.");
                }
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error");
        }
    }

}
