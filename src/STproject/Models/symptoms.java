package STproject.Models;

public class Symptoms {

    private String patientCPR;
    private String bladderCapacity;
    private int IEsPerDay;
    private int UEsPerDay;
    private int urinationPerDay;
    private int nocturiaEpisodes;
    private String other;

    public Symptoms(String patientCPR, String bladderCapacity, int IEsPerDay, int UEsPerDay, int urinationPerDay, int nocturiaEpisodes, String other) {
        this.patientCPR = patientCPR;
        this.bladderCapacity = bladderCapacity;
        this.IEsPerDay = IEsPerDay;
        this.UEsPerDay = UEsPerDay;
        this.urinationPerDay = urinationPerDay;
        this.nocturiaEpisodes = nocturiaEpisodes;
        this.other = other;
    }

    public String getPatientCPR() {
        return patientCPR;
    }

    public String getBladderCapacity() {
        return bladderCapacity;
    }

    public int getIEsPerDay() {
        return IEsPerDay;
    }

    public int getUEsPerDay() {
        return UEsPerDay;
    }

    public int getUrinationPerDay() {
        return urinationPerDay;
    }

    public int getNocturiaEpisodes() {
        return nocturiaEpisodes;
    }

    public String getOther() {
        return other;
    }

    public void setPatientCPR(String patientCPR) {
        this.patientCPR = patientCPR;
    }

    public void setBladderCapacity(String bladderCapacity) {
        this.bladderCapacity = bladderCapacity;
    }

    public void setIEsPerDay(int IEsPerDay) {
        this.IEsPerDay = IEsPerDay;
    }

    public void setUEsPerDay(int UEsPerDay) {
        this.UEsPerDay = UEsPerDay;
    }

    public void setUrinationPerDay(int urinationPerDay) {
        this.urinationPerDay = urinationPerDay;
    }

    public void setNocturiaEpisodes(int nocturiaEpisodes) {
        this.nocturiaEpisodes = nocturiaEpisodes;
    }

    public void setOther(String other) {
        this.other = other;
    }
}
