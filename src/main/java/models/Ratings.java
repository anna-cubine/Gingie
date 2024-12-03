package models;

public class Ratings {
    private int ratingID;
    private int userID;
    private int recipeID;
    private int stars;
    private String timestamp;

    public Ratings(int ratingID, int userID, int recipeID, int stars, String timestamp) {
        this.ratingID = ratingID;
        this.userID = userID;
        this.recipeID = recipeID;
        this.stars = stars;
        this.timestamp = timestamp;
    }

    public int getRatingID() { return ratingID; }
    public int getUserID() { return userID; }
    public int getRecipeID() { return recipeID; }
    public int getStars() { return stars; }
    public String getTimestamp() { return timestamp; }

    public void setRatingID(int ratingID) { this.ratingID = ratingID; }
    public void setUserID(int userID) { this.userID = userID; }
    public void setRecipeID(int recipeID) { this.recipeID = recipeID; }
    public void setStars(int stars) { this.stars = stars; }
    public void setTimestamp(String timestamp) { this.timestamp = timestamp; }
}
