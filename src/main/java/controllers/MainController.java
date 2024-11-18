package controllers;

import models.RecipeModel;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import repositories.RecipeRepository;
import services.RecipeService;

import java.io.File;
import java.io.IOException;
import java.util.List;

@Controller
public class MainController {
    private final RecipeService recipeService;
    private static int recipeCounter = 0;
    private final RecipeRepository recipeRepository;

    public MainController(RecipeService recipeService, RecipeRepository recipeRepository) {
        this.recipeService = recipeService;
        this.recipeRepository = recipeRepository;
    }

    @GetMapping("/category")
    public String category(Model model) {
        System.out.println("Category");
        return "category.html";
    }

    //Keeping this for when we use specific IDs to see the recipe page
    @GetMapping("/recipe{id}")
    public String recipe(@PathVariable("id") Long recipeID, Model model) {
        List<RecipeModel> recipes = recipeService.getRecipe();
        model.addAttribute("recipes", recipes);
        return "recipe.html";
    }

    @GetMapping("/addRecipe")
    public String addRecipe(Model model) {
        model.addAttribute("recipe", new RecipeModel());
        return "addRecipe";
    }

    /*
    @PostMapping("/addRecipe")
    public String addRecipe(@ModelAttribute("recipe") RecipeModel recipe,
                            @RequestParam("dishName") String dishName,
                            @RequestParam("image") MultipartFile image,
                            @RequestParam(value = "ingredients") List<String> ingredients,
                            @RequestParam(value = "instructions") List<String> instructions,
                            @RequestParam("categoryID") int categoryID,
                            Model model) {
        //Image upload handling
        String imagePath = null;
        if (image != null && !image.isEmpty()) {
            try{
                //Save image to images folder
                String uploadPath = "images";
                File file = new File(uploadPath + image.getOriginalFilename());
                image.transferTo(file);
                imagePath = file.getAbsolutePath();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        //Joining forms together to work inside a TEXT int the database, using \n as delimiter
        String ingredientsString = String.join("\n", ingredients);
        String instructionsString = String.join("\n", instructions);
        //Making sure there is unique IDs for each recipe
        int recipeID = recipeCounter++;
        //Create a new recipe model and save
        recipe = new RecipeModel(recipeID, dishName, ingredientsString, instructionsString, categoryID, 0.0);
        recipeService.saveRecipe(recipe);
        return "redirect:/browse";
    }*/

    @PostMapping("/addRecipe")
    public String addRecipe(@ModelAttribute RecipeModel recipe, RedirectAttributes redirectAttributes) {
        recipeRepository.saveRecipe(recipe);
        redirectAttributes.addFlashAttribute("successMessage", "Recipe added successfully");
        return "redirect:/browse";
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
