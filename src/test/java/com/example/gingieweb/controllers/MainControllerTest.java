package com.example.gingieweb.controllers;


import controllers.MainController;
import models.RecipeModel;
import models.Users;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import services.RecipeService;
import services.UserService;

import static org.mockito.ArgumentMatchers.anyInt;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@WebMvcTest(MainController.class)
public class MainControllerTest {

    //Getting a mock mvc for the test
    @Autowired
    private MockMvc mockMvc;

    //Creating a fake bean for recipeService
    @MockBean
    private RecipeService recipeService;

    //Creating a fake bean for userService
    @MockBean
    private UserService userService;

    /**
     * Testing if /recipe works in the main controller
     * @throws Exception Catching any unexpected errors
     */
    @Test
    void testRecipeEndpoint() throws Exception {
        //Create a fake recipe model with some dummy data..
        RecipeModel recipe = new RecipeModel();
        recipe.setRecipeID(0);
        recipe.setDishName("Test recipe");
        Mockito.when(recipeService.getRecipeById(anyInt())).thenReturn(recipe);

        //GET and verify response
        mockMvc.perform(get("/recipe").param("recipeID", "0"))
                .andExpect(status().isOk())
                .andExpect(view().name("recipe"))
                .andExpect(model().attributeExists("recipe"));
    }

    @Test
    void testAddRecipeEndpoint() throws Exception {
        RecipeModel recipe = new RecipeModel();
        recipe.setRecipeID(0);
        recipe.setDishName("Test recipe");
        //Mockito.when(recipeService.saveRecipe(recipe));
    }

    /**
     * Testing if User model works with login in main controller
     * @throws Exception
     */
    @Test
    void testLoginEndpoint() throws Exception {
        //Create a fake user with some dummy data...
        Users user = new Users();
        user.setUserID(0);
        user.setUsername("Test username");
        user.setEmail("Test@test.com");
        user.setPassword("Test pass");
        Mockito.when(userService.getUser(anyInt())).thenReturn(user);

        //GET and verify response
        mockMvc.perform(get("/login").param("username", "Test username"))
                .andExpect(status().isOk())
                .andExpect(view().name("user"))
                .andExpect(model().attributeExists("user"));
    }
}
