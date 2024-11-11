package models;

import java.util.ArrayList;
import java.util.List;

public class RecipeModel {
    private int recipeID;
    private String dishName;
    private List<String> ingredients;
    private List<String> instructions;
    private int categoryID;
    private double averageRating;

    public RecipeModel(int recipeID, String dishName, String ingredients, String instructions, int categoryID, double averageRating){
        this.recipeID = recipeID;
        this.dishName = dishName;
        this.ingredients = stringToList(ingredients);
        this.instructions = stringToList(instructions);
        this.categoryID = categoryID;
        this.averageRating = averageRating;
    }

    /**
     * This method will parse the string input into a list using \n as a delimiter.
     * This will make the different ingredients and instruction easier to print when
     * viewing a recipe
     * @return
     */
    public List<String> stringToList(String input) {
        String[] splitString = input.split("\n");
        List<String> strList = new ArrayList<String>(splitString.length);
        for (String s : strList)
            strList.add(s.trim());
        return strList;
    }
}
