package models;

import java.util.ArrayList;
import java.util.List;

public class RecipeModel {
    private int recipeID;
    private String dishName;
    private java.util.List<String> ingredients;
    private java.util.List<String> instructions;
    private String categoryID;
    private double averageRating;
    private String imagePath;

    private int ratingCounter;

    //No argument constructor for form binding
    public RecipeModel() {
    }

    public RecipeModel(int recipeID, String dishName, String ingredients, String instructions, String categoryID, double averageRating, String imagePath){
        this.recipeID = recipeID;
        this.dishName = dishName;
        this.ingredients = stringToList(ingredients);
        this.instructions = stringToList(instructions);
        this.categoryID = categoryID;
        this.averageRating = averageRating;
        this.imagePath = imagePath;
    }
    
    
    /**
     * This method will parse the string input into a list using \n as a delimiter.
     * This will make the different ingredients and instruction easier to print when
     * viewing a recipe
     * @return List verison of the string
     */
    public java.util.List<String> stringToList(String input) {
        if (input == null || input.isEmpty()) {
            return new java.util.ArrayList<>();
        }
        String[] splitString = input.split("\n");
        java.util.List<String> strList = new java.util.ArrayList<String>(splitString.length);
        for (String s : splitString)
            strList.add(s.trim());
        return strList;
    }

    /**
     * Method will turn list back into string for saving to the database
     * @param input List given by user
     * @return If the list is empty, return an empty string. Otherwise join strings with \n as a delimiter
     */
    public String listToString(java.util.List<String> input) {
        if (input == null || input.isEmpty()){
            return "";
        }
        return String.join("\n", input);
    }

    //Getters and setters
    public int getRecipeID() {return recipeID;}
    public String getDishName() {return dishName;}
    public java.util.List<String> getIngredients() {return ingredients;}
    public java.util.List<String> getInstructions() {return instructions;}
    public String getCategoryID() {return categoryID;}
    public double getAverageRating() {return averageRating;}
    public int getRatingCounter() {return ratingCounter;}
    public String getimagePath() {return imagePath;}

    public void setRecipeID(int recipeID) {this.recipeID = recipeID;}
    public void setDishName(String dishName) {this.dishName = dishName;}
    public void setIngredients(java.util.List<String> ingredients) {this.ingredients = ingredients;}
    public void setInstructions(java.util.List<String> instructions) {this.instructions = instructions;}
    public void setCategoryID(String categoryID) {this.categoryID = categoryID;}
    public void setAverageRating(double averageRating) {this.averageRating = averageRating;}
    public void setRatingCounter(int ratingCounter) {this.ratingCounter = ratingCounter;}
    public void setimagePath(String imagePath) {this.imagePath = imagePath;}
}
