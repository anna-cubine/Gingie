package com.example.gingieweb.repositories;

import models.RecipeModel;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.jdbc.core.JdbcTemplate;
import repositories.RecipeRepository;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
public class RecipeRepositoryTest {

    @Autowired
    private RecipeRepository recipeRepository;

    @Mock
    JdbcTemplate jdbcTemplate;

    @Test
    void testSaveRecipe() {
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

        recipeRepository.saveRecipe(recipe);

        RecipeModel expectedRecipe = recipeRepository.getRecipeById(recipe.getRecipeID());
        assertEquals("Test recipe", expectedRecipe.getDishName());
    }
}
