package controllers;

import models.RecipeModel;
import models.Users;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import services.RecipeService;
import services.UserService;

import java.util.List;

@Controller
public class MainController {
    private final RecipeService recipeService;
    private final UserService userService;

    public MainController(RecipeService recipeService, UserService userService) {
        this.recipeService = recipeService;
        this.userService = userService;
    }

    /**
     * This is used for initial launch of the application. Since the application wants to start on
     * category.html, and we want it to go to the category page, we set it up so the page will redirect
     * to the category page on launch
     * @return redirecting to /category
     */
    @GetMapping("/category.html")
    public String redirectToCategory() {
        return "redirect:/category";
    }

    /**
     * Category page with various categories to click on to move the user to browse. When a category is clicked
     * the controller will grab whichever recipes match that category ID and drop them in browse
     * @return category page
     */
    @GetMapping("/category")
    public String category() {
        return "category";
    }

    /**
     * Mapping for the recipe detail page. Get the recipe from the service then make
     * sure to get the ingredients and instructions as lists to show in view
     * @param recipeID ID for specific recipe
     * @param model
     * @return
     */
    @GetMapping("/recipe")
    public String recipe(@RequestParam("id") int recipeID, Model model) {
        RecipeModel recipe = recipeService.getRecipeById(recipeID);
        //Have to turn string from database back into list
        //So I'll just grab it from the getter in the model
        recipe.setIngredients(recipe.getIngredients());
        recipe.setInstructions(recipe.getInstructions());
        model.addAttribute("recipe", recipe);
        return "recipe";
    }

    /**
     * Add recipe page using spring boot mapping
     * @param model Gets a model to add recipe model to
     * @return Returns the path of where the user is being taken
     */
    @GetMapping("/addRecipe")
    public String addRecipe(Model model) {
        model.addAttribute("recipe", new RecipeModel());
        return "addRecipe";
    }

    /*NEEDED FOR IMAGE ENCODING
      @RequestParam("image") MultipartFile image,*/

    /**
     * Post mapping for the recipe. This is used to save the recipe, moving through the layers closer to
     * the database
     * @param recipe Taking the populated recipe model data given from the user in the forms
     * @return Redirecting the user to the browse page
     */
    @PostMapping("/addRecipe")
    public String addRecipe(@ModelAttribute RecipeModel recipe) {
        recipeService.saveRecipe(recipe);
        //Image upload handling
        /*
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
        }*/
        return "redirect:/browse";
    }

    /**
     * Add user page using spring boot mapping
     * @param model Gets a model to add User model to
     * @return Returns the path of where the user is being taken
     */
    @GetMapping("/register")
    public String register(Model model) {
        model.addAttribute("user", new Users());
        return "register";
    }
    /**
     * Post mapping for the recipe. This is used to save the recipe, moving through the layers closer to
     * the database
     * @param user Taking the populated recipe model data given from the user in the forms
     * @return Redirecting the user to the browse page
     */
    @PostMapping("/register")
    public String register(@ModelAttribute Users user) {
        userService.addUser(user);
        return "redirect:/login";
    }

    @GetMapping("/favorites")
    public String favorites(Model model) {
        return "favorites";
    }

    @GetMapping("/login")
    public String login(Model model) {
        return "login";
    }

    @GetMapping("/browseBreakfast")
    public String browseBreakfast(Model model) {
        List<RecipeModel> recipes = recipeService.getRecipeByCategory("Breakfast");
        model.addAttribute("recipes", recipes);
        return "browse";
    }

    @GetMapping("/browseLunch")
    public String browseLunch(Model model) {
        List<RecipeModel> recipes = recipeService.getRecipeByCategory("Lunch");
        model.addAttribute("recipes", recipes);
        return "browse";
    }

    @GetMapping("/browseDinner")
    public String browseDinner(Model model) {
        List<RecipeModel> recipes = recipeService.getRecipeByCategory("Dinner");
        model.addAttribute("recipes", recipes);
        return "browse";
    }

    @GetMapping("/browseDesert")
    public String browseDesert(Model model) {
        List<RecipeModel> recipes = recipeService.getRecipeByCategory("Desert");
        model.addAttribute("recipes", recipes);
        return "browse";
    }

    @GetMapping("/browseSnacks")
    public String browseSnacks(Model model) {
        List<RecipeModel> recipes = recipeService.getRecipeByCategory("Snacks");
        model.addAttribute("recipes", recipes);
        return "browse";
    }

    @GetMapping("/browseDrinks")
    public String browseDrinks(Model model) {
        List<RecipeModel> recipes = recipeService.getRecipeByCategory("Drinks");
        model.addAttribute("recipes", recipes);
        return "browse";
    }

    @GetMapping("/browse")
    public String browse(Model model) {
        List<RecipeModel> recipes = recipeService.getAllRecipes();
        model.addAttribute("recipes", recipes);
        return "browse";
    }
}
