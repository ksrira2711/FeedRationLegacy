package Bean;

/**
 * Created by Sriram Kumar on 8/6/2017.
 */

public class FeedBean {
    private final String feedName;
    private final double feedCp;
    private final double feedDm;
    private final double feedTDN;
    private final double feedCa;
    private final double feedP;
    private final double feedCost;
    private double feedWeight;
    private double feedMaxWeight;

    public static class Builder {
        private final String feedName;
        private double feedCp, feedDm, feedTDN, feedCa, feedP, feedWeight, feedCost;

        public Builder (String feedName){
            this.feedName = feedName;
        }
        public Builder cost(double feedCost){
            this.feedCost = feedCost;
            return this;
        }
        public Builder DM(double feedDm){
            this.feedDm = feedDm;
            return this;
        }
        public Builder cpAndTdn(double CP, double TDN){
            this.feedCp = CP;
            this.feedTDN = TDN;
            return this;
        }
        public Builder caAndPh(double Ca, double Ph){
            this.feedCa = Ca;
            this.feedP = Ph;
            return this;
        }
        public Builder weight(double weight){
            this.feedWeight = weight;
            return this;
        }
        public FeedBean build(){
            return new FeedBean(this);
        }
    }

    public FeedBean(Builder builder) {
        feedName = builder.feedName;
        feedCost = builder.feedCost;
        feedDm = builder.feedDm;
        feedCp = builder.feedCp;
        feedTDN = builder.feedTDN;
        feedCa = builder.feedCa;
        feedP = builder.feedP;
        feedWeight = builder.feedWeight;
    }

    public void setFeedWeight(double feedWeight){
        this.feedWeight = feedWeight;
    }

    public void computeWeight(double dmFromFeedType, double totalTdnFromFeedType){
        double weight = dmFromFeedType * this.feedTDN / totalTdnFromFeedType;
        weight = weight/this.feedDm * 100;
        this.setFeedWeight(weight);
    }

    public double getDryWeight(){
        return this.getFeedWeight() * this.getFeedDm() / 100;
    }

    public double getFeedCp() {
        return feedCp;
    }

    public double getFeedDm() {
        return feedDm;
    }

    public double getFeedTDN() {
        return feedTDN;
    }

    public double getFeedCa() {
        return feedCa;
    }

    public double getFeedP() {
        return feedP;
    }

    public double getFeedCost() {
        return feedCost;
    }

    public double getFeedWeight(){
        return this.feedWeight;
    }

    public String getFeedName(){
        return this.feedName;
    }

    public double getFeedMaxWeight() {
        return feedMaxWeight;
    }

    public void setFeedMaxWeight(double feedMaxWeight) {
        this.feedMaxWeight = feedMaxWeight;
    }
}
