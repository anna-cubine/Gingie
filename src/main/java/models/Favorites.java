package models;

public class Favorites {
    private int userID;
    private int recipeID;

    private int recipeIDCounter;

    public Favorites(int userID, int recipeID) {
        this.userID = userID;
        this.recipeID = recipeID;
    }

    public int getUserID() { return userID; }
    public int getRecipeID() { return recipeID; }
    public int getRecipeIDCounter() { return recipeIDCounter; }

    public void setUserID(int userID) { this.userID = userID; }
    public void setRecipeID(int recipeIE) { this.recipeID = recipeIE; }
    public void setRecipeIDCounter(int recipeIDCounter) { this.recipeIDCounter = recipeIDCounter; }
}
