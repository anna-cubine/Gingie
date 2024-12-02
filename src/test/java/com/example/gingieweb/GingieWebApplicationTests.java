package com.example.gingieweb;

import com.example.gingieweb.controllers.MainControllerTest;
import com.example.gingieweb.models.RecipeModelTest;
import com.example.gingieweb.repositories.RecipeRepositoryTest;
import com.example.gingieweb.services.RecipeServiceTest;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import run.GingieWebApplication;

@SpringBootTest(classes = GingieWebApplication.class)
@TestPropertySource(locations = "classpath:application.properties")
class GingieWebApplicationTests {

    @Test
    void contextLoads() {
        MainControllerTest controller = new MainControllerTest();
        RecipeModelTest recipe = new RecipeModelTest();
        RecipeRepositoryTest recipeRepository = new RecipeRepositoryTest();
        RecipeServiceTest recipeService = new RecipeServiceTest();
        System.out.println("Tests loaded");
    }

}
