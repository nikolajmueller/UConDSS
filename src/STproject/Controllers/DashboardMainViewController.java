
package STproject.Controllers;

import javafx.fxml.FXML;


public class DashboardMainViewController {

    @FXML public DashboardEffectivenessScoreController dashboardEffectivenessScoreController;
    @FXML public DashboardPatientStatisticTreatmentController dashboardPatientStatisticTreatmentController;
    @FXML public DashboardSymptomEvaluationController dashboardSymptomEvaluationController;
    @FXML public DashboardTreatmentStrategyController dashboardTreatmentStrategyController;
    @FXML public DashboardUconDataVisualizationController dashboardUconDataVisualizationController;
    
    @FXML private void initialize(){
        dashboardEffectivenessScoreController.initialize(this);
        dashboardPatientStatisticTreatmentController.initialize(this);
        dashboardSymptomEvaluationController.initialize(this);
        dashboardTreatmentStrategyController.initialize(this);
        dashboardUconDataVisualizationController.initialize(this);
        
}

}
