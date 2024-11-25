package models;

import java.util.ArrayList;
import java.util.List;

public class RecipeModel {
    private int recipeID;
    private String dishName;
    private List<String> ingredients;
    private List<String> instructions;
    private String categoryID;
    private double averageRating;

    //No argument constructor for form binding
    public RecipeModel() {
    }

    public RecipeModel(int recipeID, String dishName, String ingredients, String instructions, String categoryID, double averageRating){
        this.recipeID = recipeID;
        this.dishName = dishName;
        this.ingredients = stringToList(ingredients);
        this.instructions = stringToList(instructions);
        this.categoryID = categoryID;
    }

    /**
     * This method will parse the string input into a list using \n as a delimiter.
     * This will make the different ingredients and instruction easier to print when
     * viewing a recipe
     * @return List verison of the string
     */
    public List<String> stringToList(String input) {
        if (input == null || input.isEmpty()) {
            return new ArrayList<>();
        }
        String[] splitString = input.split("\n");
        List<String> strList = new ArrayList<String>(splitString.length);
        for (String s : strList)
            strList.add(s.trim());
        return strList;
    }

    /**
     * Method will turn list back into string for saving to the database
     * @param input List given by user
     * @return If the list is empty, return an empty string. Otherwise join strings with \n as a delimiter
     */
    public String listToString(List<String> input) {
        if (input == null || input.isEmpty()){
            return "";
        }
        return String.join("\n", input);
    }

    //Getters and setters
    public int getRecipeID() {return recipeID;}
    public String getDishName() {return dishName;}
    public List<String> getIngredients() {return ingredients;}
    public List<String> getInstructions() {return instructions;}
    public String getCategoryID() {return categoryID;}
    public double getAverageRating() {return averageRating;}

    public void setRecipeID(int recipeID) {this.recipeID = recipeID;}
    public void setDishName(String dishName) {this.dishName = dishName;}
    public void setIngredients(List<String> ingredients) {this.ingredients = ingredients;}
    public void setInstructions(List<String> instructions) {this.instructions = instructions;}
    public void setCategoryID(String categoryID) {this.categoryID = categoryID;}
    public void setAverageRating(double averageRating) {this.averageRating = averageRating;}
}
