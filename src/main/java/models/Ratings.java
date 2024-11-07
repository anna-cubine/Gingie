package models;

public class Ratings {
    private int ratingID;
    private int stars;
    private String timestamp;

    public Ratings(int ratingID, int stars, String timestamp) {
        this.ratingID = ratingID;
        this.stars = stars;
        this.timestamp = timestamp;
    }
}
