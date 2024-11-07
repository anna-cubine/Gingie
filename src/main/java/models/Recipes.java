package models;

import java.util.List;

public class Recipes {
    private int recipeID;
    private String dishName;
    private List<String> ingredients;
    private List<String> instructions;
    private double averageRating;

    public Recipes(int recipeID, String dishName, List<String> ingredients, List<String> instructions, double averageRating){
        this.recipeID = recipeID;
        this.dishName = dishName;
        this.ingredients = ingredients;
        this.instructions = instructions;
        this.averageRating = averageRating;
    }
}
