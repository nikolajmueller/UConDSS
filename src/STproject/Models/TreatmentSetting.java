package STproject.Models;

/**
 *
 * @author Nikol
 */
public class TreatmentSetting {

    int treatmentNumber;
    String timeLimitedSetting;
    int timeLimitedIntensity;
    String urgeSetting;
    int urgeIntensity;

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
