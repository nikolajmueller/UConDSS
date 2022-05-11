package STproject.Models;

public class SymptomEffect {

    private double postIEs;
    private double postUEs;
    private double postUrination;
    private double postNocturia;
    private double IEsScore;
    private double UEsScore;
    private double urinationScore;
    private double nocturiaScore;
    private int overallEffectivessScore;

    public int getOverallEffectivessScore() {
        return overallEffectivessScore;
    }

    public double getPostIEs() {
        return postIEs;
    }

    public double getPostUEs() {
        return postUEs;
    }

    public double getPostUrination() {
        return postUrination;
    }

    public double getPostNocturia() {
        return postNocturia;
    }

    public double getIEsScore() {
        return IEsScore;
    }

    public double getUEsScore() {
        return UEsScore;
    }

    public double getUrinationScore() {
        return urinationScore;
    }

    public double getNocturiaScore() {
        return nocturiaScore;
    }

    public void setOverallEffectivessScore(int overallEffectivessScore) {
        this.overallEffectivessScore = overallEffectivessScore;
    }

    public void setPostIEs(int postIEs) {
        this.postIEs = postIEs;
    }

    public void setPostUEs(int postUEs) {
        this.postUEs = postUEs;
    }

    public void setPostUrination(int postUrination) {
        this.postUrination = postUrination;
    }

    public void setPostNocturia(int postNocturia) {
        this.postNocturia = postNocturia;
    }

    public void setIEsScore(double IEsScore) {
        this.IEsScore = IEsScore;
    }

    public void setUEsScore(double UEsScore) {
        this.UEsScore = UEsScore;
    }

    public void setUrinationScore(double urinationScore) {
        this.urinationScore = urinationScore;
    }

    public void setNocturiaScore(double nocturiaScore) {
        this.nocturiaScore = nocturiaScore;
    }

}
