package com.example.gingieweb.services;

import models.RecipeModel;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import repositories.RecipeRepository;
import services.RecipeService;

import static org.junit.jupiter.api.Assertions.assertEquals;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class RecipeServiceTest {

    @Mock
    private RecipeRepository recipeRepository;

    @InjectMocks
    private RecipeService recipeService;

    /**
     * Testing to get recipe by an ID using getRecipeById method
     */
    @Test
    void testGetRecipeById() {
        RecipeModel recipeModel = new RecipeModel();
        recipeModel.setRecipeID(0);
        recipeModel.setDishName("Test recipe");

        when(recipeRepository.getRecipeById(1)).thenReturn(recipeModel);

        RecipeModel result = recipeService.getRecipeById(1);
        assertEquals("Test recipe", result.getDishName());
    }
}
