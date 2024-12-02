package com.example.gingieweb.models;

import models.RecipeModel;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class RecipeModelTest {

    /**
     * Testing to see if the RecipeModel is working as intended
     */
    @Test
    void testRecipeModel() {
        //Starting with some lists to put ingredients and instructions in
        List<String> ingredients = Arrays.asList("Ingredient one", "Ingredient two");
        List<String> instructions = Arrays.asList("Instruction one", "Instruction two");

        //Creating new model and populating with data
        RecipeModel recipe = new RecipeModel();
        recipe.setRecipeID(0);
        recipe.setDishName("Test recipe");
        recipe.setIngredients(ingredients);
        recipe.setInstructions(instructions);
        recipe.setCategoryID("Lunch");

        //Using assertEquals to check if they're the same
        assertEquals(0, recipe.getRecipeID());
        assertEquals("Test recipe", recipe.getDishName());
        assertEquals(ingredients, recipe.getIngredients());
        assertEquals(instructions, recipe.getInstructions());
        assertEquals("Lunch", recipe.getCategoryID());
    }
}
