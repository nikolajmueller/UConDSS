package STproject.Controllers;

import static STproject.Main.Main.patient;
import static STproject.Main.Main.symptoms;
import STproject.Models.DatabaseHandler;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.XYChart;
import javax.swing.JOptionPane;

public class TreatmentGraphController implements Initializable {

    @FXML
    private BarChart<String, Integer> barChart;

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        barChart.setAnimated(false);
        try {
            Connection conn = DatabaseHandler.getConnection();

            PreparedStatement psCount = conn.prepareStatement("SELECT COUNT(*) FROM SymptomsBaseline WHERE patientCPR = ?");
            psCount.setString(1, patient.getCprNumber());
            ResultSet rsCount = psCount.executeQuery();
            rsCount.next();
            int count = rsCount.getInt(1);

            if (count != 0) {
                for (int i = 1; i <= count; i++) {
                    PreparedStatement ps = conn.prepareStatement(
                            "SELECT IEsPerDay, UEsPerDay, UrinationPerDay,"
                            + " NocturiaEpisodes FROM SymptomsBaseline "
                            + "WHERE patientCPR = ? AND symptomsIndex = ?");
                    ps.setString(1, patient.getCprNumber());
                    ps.setInt(2, i);
                    ResultSet rs = ps.executeQuery();
                    rs.next();

                    XYChart.Series<String, Integer> seriesAdd = new XYChart.Series();
                    seriesAdd.setName("" + i);
                    seriesAdd.getData().add(new XYChart.Data("IEs", rs.getInt(1)));
                    seriesAdd.getData().add(new XYChart.Data("UEs", rs.getInt(2)));
                    seriesAdd.getData().add(new XYChart.Data("Urination", rs.getInt(3)));
                    seriesAdd.getData().add(new XYChart.Data("Nocturia Ep", rs.getInt(4)));

                    barChart.getData().addAll(seriesAdd);

                }

            } else {
                JOptionPane.showMessageDialog(null, "Error treatmentExpandGraph1");
            }

        } catch (Exception e) {
        }

    }
}
