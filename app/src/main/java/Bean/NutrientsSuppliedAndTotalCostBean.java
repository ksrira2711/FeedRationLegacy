package Bean;

public class NutrientsSuppliedAndTotalCostBean {
    private double totalDM, totalCP, totalTDN, totalCA, totalPH, roughage, concentrate, totalCost;

    public void increment(double dryWeight, double feedCp, double feedTDN, double feedCa, double feedP, double caAbsorbable, double pAbsorbable){
        this.totalDM += dryWeight;
        this.totalCP += dryWeight * feedCp / 100;
        this.totalTDN += dryWeight * feedTDN / 100;
        this.totalCA += dryWeight * feedCa * caAbsorbable/ 100;
        this.totalPH += dryWeight * feedP * pAbsorbable / 100;
    }

    public void incrementRoughageConcentrateAndCost(double delta, boolean isRoughage, double costDelta){
        this.roughage += ((isRoughage) ? delta : 0);
        this.concentrate += ((!isRoughage) ? delta : 0);
        this.totalCost += costDelta;
    }

    public double getTotalDM() {
        return totalDM;
    }

    public double getTotalCP() {
        return totalCP;
    }

    public double getTotalTDN() {
        return totalTDN;
    }

    public double getTotalCA() {
        return totalCA;
    }

    public double getTotalPH() {
        return totalPH;
    }

    public double getRoughage() {
        return roughage;
    }

    public double getConcentrate() {
        return concentrate;
    }

    public double getTotalCost() {
        return totalCost;
    }
}
