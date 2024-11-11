package controllers;

import models.RecipeModel;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import services.RecipeService;

import java.util.List;

@Controller
public class MainController {
    private final RecipeService recipeService;

    public MainController(RecipeService recipeService) {
        this.recipeService = recipeService;
    }

    @GetMapping("/category")
    public String index() {
        return "category";
    }

    @GetMapping("/recipe")
    public String recipe(Model model) {
        List<RecipeModel> recipes = recipeService.getRecipe();
        model.addAttribute("recipes", recipes);
        return "recipe";
    }
}
