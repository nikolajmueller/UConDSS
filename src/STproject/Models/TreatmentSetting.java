package STproject.Models;

public class TreatmentSetting {

    private int treatmentNumber;
    private String timeLimitedSetting;
    private int timeLimitedIntensity;
    private String urgeSetting;
    private int urgeIntensity;

        
    
    public void setTreatmentNumber(int treatmentNumber) {
        this.treatmentNumber = treatmentNumber;
    }

    public void setTimeLimitedSetting(String timeLimitedSetting) {
        this.timeLimitedSetting = timeLimitedSetting;
    }

    public void setTimeLimitedIntensity(int timeLimitedIntensity) {
        this.timeLimitedIntensity = timeLimitedIntensity;
    }

    public void setUrgeSetting(String urgeSetting) {
        this.urgeSetting = urgeSetting;
    }

    public void setUrgeIntensity(int urgeIntensity) {
        this.urgeIntensity = urgeIntensity;
    }

    public int getTreatmentNumber() {
        return treatmentNumber;
    }

    public String getTimeLimitedSetting() {
        return timeLimitedSetting;
    }

    public int getTimeLimitedIntensity() {
        return timeLimitedIntensity;
    }

    public String getUrgeSetting() {
        return urgeSetting;
    }

    public int getUrgeIntensity() {
        return urgeIntensity;
    }
}
