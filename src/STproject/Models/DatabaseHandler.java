/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package STproject.Models;

import STproject.Main.Main;
import static STproject.Main.Main.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javax.swing.JOptionPane;

public class DatabaseHandler {

    public static ObservableList<Patient> ob = FXCollections.observableArrayList();
    public static ObservableList<TreatmentSetting> ob_treatment = FXCollections.observableArrayList();
    public static ObservableList<TreatmentSetting> ob_treatmentRecommendation = FXCollections.observableArrayList();

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

    public static Patient readPatient() {

        try {
            Connection conn = DatabaseHandler.getConnection();
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM PatientList WHERE clinicianID = ?");
            ps.setInt(1, clinician.getId());
            ResultSet rs_patient = ps.executeQuery();

            while (rs_patient.next()) {
                ob.add(new Patient(rs_patient.getString("CPR"), rs_patient.getString("Name"), rs_patient.getInt("Age"), rs_patient.getString("Gender")));
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
                        + " (clinicianID, CPR, Name, Age, Gender)"
                        + " VALUES (?, ?, ?, ?, ?)");
                psCreatePatient.setInt(1, clinician.getId());
                psCreatePatient.setString(2, CPR);
                psCreatePatient.setString(3, name);
                psCreatePatient.setInt(4, age);
                psCreatePatient.setString(5, gender);
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

    public static void saveSymptonsToDb() {
        try {
            Connection conn = DatabaseHandler.getConnection();

// check om CPR allerede har registeret baseline symptoms
            PreparedStatement psCheckIfExists = conn.prepareStatement("SELECT COUNT(*) FROM SymptomsBaseline AS countrow WHERE patientCPR = ?");
            psCheckIfExists.setString(1, patient.getCprNumber());
            ResultSet rsIfExists = psCheckIfExists.executeQuery();
            rsIfExists.next();
            int countrow = rsIfExists.getInt(1);
            countrow++;
// hvis NOT EXISTS (0), gem symptomer til DB

            PreparedStatement ps = conn.prepareStatement(""
                    + "INSERT INTO SymptomsBaseline"
                    + " (patientCPR, symptomsIndex, bladderCapacity, IEsPerDay,"
                    + " UEsPerDay, UrinationPerDay, NocturiaEpisodes, Other)"
                    + " VALUES (?,?,?,?,?,?,?,?)");
            ps.setString(1, patient.getCprNumber());
            ps.setInt(2, countrow);
            ps.setString(3, symptoms.getBladderCapacity());
            ps.setInt(4, symptoms.getIEsPerDay());
            ps.setInt(5, symptoms.getUEsPerDay());
            ps.setInt(6, symptoms.getUrinationPerDay());
            ps.setInt(7, symptoms.getNocturiaEpisodes());
            ps.setString(8, symptoms.getOther());
            ps.execute();
            conn.close();

// hvis DO EXISTS (1), send fejlmeddelelse
        } catch (SQLException p) {
            System.err.println("Cannot connect to database server");
        }
    }

    public static void saveTreatmentToDb() {
        try {
            Connection conn = DatabaseHandler.getConnection();

            PreparedStatement psCheckIfExists = conn.prepareStatement(
                    "SELECT DISTINCT COUNT(treatmentNumber) FROM patientTreatment WHERE CPR = ?");
            psCheckIfExists.setString(1, patient.getCprNumber());
            ResultSet rsIfExists = psCheckIfExists.executeQuery();
            rsIfExists.next();
            int countrow = rsIfExists.getInt(1);

            String currentDate = LocalDate.now().toString();

            if (countrow == 0) {
                countrow = 1;
                treatmentSetting.setTreatmentNumber(countrow);
                PreparedStatement ps = conn.prepareStatement(""
                        + "INSERT INTO `patientTreatment`(`CPR`, `treatmentNumber`, `timeLimitedSetting`,"
                        + " `timeLimitedIntensity`, `urgeSetting`, `urgeIntensity`, `dateSaved`)"
                        + " VALUES (?,?,?,?,?,?,?)");
                ps.setString(1, patient.getCprNumber());
                ps.setInt(2, countrow);
                ps.setString(3, treatmentSetting.getTimeLimitedSetting());
                ps.setInt(4, treatmentSetting.getTimeLimitedIntensity());
                ps.setString(5, treatmentSetting.getUrgeSetting());
                ps.setInt(6, treatmentSetting.getUrgeIntensity());
                ps.setString(7, currentDate);

                ps.execute();
                conn.close();
            } else {
                /*
                PreparedStatement psCheckDate = conn.prepareStatement(
                        "SELECT COUNT(*) FROM patientTreatment WHERE dateSaved = ?");
                psCheckDate.setString(1, currentDate);
                ResultSet dateExistsSQL = psCheckDate.executeQuery();
                dateExistsSQL.next();
                int dateExists = dateExistsSQL.getInt(1);
                 */
                //if (dateExists == 0) {
                countrow++;
                treatmentSetting.setTreatmentNumber(countrow);

                PreparedStatement ps = conn.prepareStatement(""
                        + "INSERT INTO `patientTreatment`(`CPR`, `treatmentNumber`, `timeLimitedSetting`,"
                        + " `timeLimitedIntensity`, `urgeSetting`, `urgeIntensity`, `dateSaved`)"
                        + " VALUES (?,?,?,?,?,?,?)");
                ps.setString(1, patient.getCprNumber());
                ps.setInt(2, countrow);
                ps.setString(3, treatmentSetting.getTimeLimitedSetting());
                ps.setInt(4, treatmentSetting.getTimeLimitedIntensity());
                ps.setString(5, treatmentSetting.getUrgeSetting());
                ps.setInt(6, treatmentSetting.getUrgeIntensity());
                ps.setString(7, currentDate);

                ps.execute();
                conn.close();
                // } else {
                //JOptionPane.showMessageDialog(null, "Patient already has treatment registered with current date.");
                //}
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error");
        }
    }

// SELECT * FROM SymptomsBaseline WHERE patientCPR =
    public static void readSymptoms() {

        try {
            Connection conn = DatabaseHandler.getConnection();
            PreparedStatement ps = conn.prepareStatement(
                    "SELECT `bladderCapacity`, `IEsPerDay`, `UEsPerDay`, `UrinationPerDay`,"
                    + " `NocturiaEpisodes`, `Other` FROM `SymptomsBaseline` "
                    + "WHERE patientCPR = ? ORDER BY symptomsIndex DESC LIMIT 1");
            ps.setString(1, patient.getCprNumber());
            ResultSet rs = ps.executeQuery();
            rs.next();

            symptoms.setBladderCapacity(rs.getString(1));
            symptoms.setIEsPerDay(rs.getInt(2));
            symptoms.setUEsPerDay(rs.getInt(3));
            symptoms.setUrinationPerDay(rs.getInt(4));
            symptoms.setNocturiaEpisodes(rs.getInt(5));
            symptoms.setOther(rs.getString(6));

            conn.close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "ERROR DatabaseHandler.readSymptoms()");
        }
    }

    public static void readLatestTreatment() {
        try {
            Connection conn = DatabaseHandler.getConnection();
            PreparedStatement ps = conn.prepareStatement(
                    "SELECT `treatmentNumber`, `timeLimitedSetting`, `timeLimitedIntensity`,"
                    + " `urgeSetting`, `urgeIntensity`"
                    + " FROM `patientTreatment` WHERE CPR = ?"
                    + " ORDER BY treatmentNumber DESC LIMIT 1");
            ps.setString(1, patient.getCprNumber());
            ResultSet rs = ps.executeQuery();
            rs.next();

            treatmentSetting.setTreatmentNumber(rs.getInt(1));
            treatmentSetting.setTimeLimitedSetting(rs.getString(2));
            treatmentSetting.setTimeLimitedIntensity(rs.getInt(3));
            treatmentSetting.setUrgeSetting(rs.getString(4));
            treatmentSetting.setUrgeIntensity(rs.getInt(5));

            conn.close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "ERROR DatabaseHandler.readTreatment()");
        }
    }

    public static TreatmentSetting readTreatmentSetting() {

        try {
            String sqlQuery = "select * from patientTreatment WHERE CPR = " + Main.patient.getCprNumber();
            ResultSet rs_treatment = getConnection().createStatement().executeQuery(sqlQuery);

            while (rs_treatment.next()) {
                ob_treatment.add(new TreatmentSetting(rs_treatment.getInt("treatmentNumber"),
                        rs_treatment.getString("timeLimitedSetting"), rs_treatment.getInt("timeLimitedIntensity"),
                        rs_treatment.getString("urgeSetting"), rs_treatment.getInt("urgeIntensity"), rs_treatment.getInt("overallEffectivenessScore"), rs_treatment.getInt("corectivenessScore")));
            }
        } catch (SQLException e) {
            System.err.println("Cannot connect to database server");
        }
        return null;
    }

    public static TreatmentSetting readTreatmentRecommendation() {

        try {
            String sqlQuery = "SELECT * FROM patientTreatment WHERE overallEffectivenessScore < -49 AND corectivenessScore = 1";
            ResultSet rs_treatment = getConnection().createStatement().executeQuery(sqlQuery);

            while (rs_treatment.next()) {
                ob_treatmentRecommendation.add(new TreatmentSetting(rs_treatment.getInt("treatmentNumber"),
                        rs_treatment.getString("timeLimitedSetting"), rs_treatment.getInt("timeLimitedIntensity"),
                        rs_treatment.getString("urgeSetting"), rs_treatment.getInt("urgeIntensity"), rs_treatment.getInt("overallEffectivenessScore"), rs_treatment.getInt("corectivenessScore")));
            }
        } catch (SQLException e) {
            System.err.println("Cannot connect to database server");
        }
        return null;
    }

    public static void saveEffectToDb() {
        try {
            Connection conn = DatabaseHandler.getConnection();
            PreparedStatement ps = conn.prepareStatement("UPDATE `patientTreatment` SET `overallEffectivenessScore` = ?"
                    + " WHERE `CPR` = ? AND treatmentNumber = ?");
            ps.setInt(1, symptomEffect.getOverallEffectivessScore());
            ps.setString(2, patient.getCprNumber());
            ps.setInt(3, treatmentSetting.getTreatmentNumber());
            ps.execute();
            conn.close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error. saveEffectToDb");
        }
    }

    public static void saveCorrectivenessToDb(int usedCorrect) {
        try {
            Connection conn = DatabaseHandler.getConnection();
            PreparedStatement ps = conn.prepareStatement("UPDATE `patientTreatment` SET `corectivenessScore` = ?"
                    + " WHERE `CPR` = ? AND treatmentNumber = ?");
            ps.setDouble(1, usedCorrect);
            ps.setString(2, patient.getCprNumber());
            ps.setInt(3, treatmentSetting.getTreatmentNumber());
            ps.execute();
            conn.close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error. saveCorrectivenesstToDb");
        }
    }

    public static int checkPatientStatus() {
        int patientStatus = 0;
        try {
            Connection conn = DatabaseHandler.getConnection();
            PreparedStatement psCheckForSymptoms = conn.prepareStatement("SELECT COUNT(*) FROM SymptomsBaseline WHERE patientCPR = ?");
            psCheckForSymptoms.setString(1, patient.getCprNumber());
            ResultSet rsSymptoms = psCheckForSymptoms.executeQuery();
            rsSymptoms.next();
            int checkForSymptoms = rsSymptoms.getInt(1);

            if (checkForSymptoms == 0) {
                patientStatus = 0;
            } else {
                PreparedStatement psCheckForTreatment = conn.prepareStatement("SELECT COUNT(*) FROM patientTreatment WHERE CPR = ?");
                psCheckForTreatment.setString(1, patient.getCprNumber());
                ResultSet rsTreatment = psCheckForTreatment.executeQuery();
                rsTreatment.next();
                int checkForTreatment = rsTreatment.getInt(1);

                if (checkForTreatment == 0) {
                    patientStatus = 1;
                } else {
                    patientStatus = 2;
                }
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error checkPatientStatus");
        }
        return patientStatus;
    }
}
