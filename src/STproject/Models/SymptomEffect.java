package STproject.Models;

public class SymptomEffect {

    double postIEs;
    double postUEs;
    double postUrination;
    double postNocturia;
    double IEsScore;
    double UEsScore;
    double urinationScore;
    double nocturiaScore;
    int overallEffectivessScore;

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
