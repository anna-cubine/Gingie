package controllers;

import models.RecipeModel;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import services.RecipeService;

import java.util.List;

@Controller
public class MainController {
    private final RecipeService recipeService;

    public MainController(RecipeService recipeService) {
        this.recipeService = recipeService;
    }

    @GetMapping("/category")
    public String category(Model model) {
        System.out.println("Category");
        return "category";
    }

    //Keeping this for when we use specific IDs to see the recipe page
    @GetMapping("/recipe{id}")
    public String recipe(@PathVariable("id") Long recipeID, Model model) {
        List<RecipeModel> recipes = recipeService.getRecipe();
        model.addAttribute("recipes", recipes);
        return "recipe";
    }

    @GetMapping("/browse")
    public String browse() {
        return "browse";
    }

    @GetMapping("/recipe")
    public String recipe(Model model) {
        return "recipe";
    }
}
