package controllers;

import jakarta.servlet.http.HttpSession;
import models.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import services.RecipeService;
import services.UserService;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;
import java.io.File;
import java.io.IOException;

@Controller
public class MainController {
    private final RecipeService recipeService;
    private final UserService userService;
    private Users currentUser;
    
    public MainController (RecipeService recipeService , UserService userService) {
        this.recipeService = recipeService;
        this.userService = userService;
    }
    
    /**
     * This is used for initial launch of the application. Since the application wants to start on
     * category.html, and we want it to go to the category page, we set it up so the page will redirect
     * to the category page on launch
     *
     * @return redirecting to /category
     */
    @GetMapping("/category.html")
    public String redirectToCategory () {
        return "redirect:/category";
    }
    
    /**
     * Category page with various categories to click on to move the user to browse. When a category is clicked
     * the controller will grab whichever recipes match that category ID and drop them in browse
     *
     * @return category page
     */
    @GetMapping("/category")
    public String category () {
        return "category";
    }
    
    /**
     * Mapping for the recipe detail page. Get the recipe from the service then make
     * sure to get the ingredients and instructions as lists to show in view
     *
     * @param recipeID ID for specific recipe
     * @param model
     * @return
     */
    @GetMapping("/recipe")
    public String recipe (@RequestParam("id") int recipeID , Model model) {
        RecipeModel recipe = recipeService.getRecipeById ( recipeID );
        //Have to turn string from database back into list
        //So I'll just grab it from the getter in the model
        recipe.setIngredients ( recipe.getIngredients ( ) );
        recipe.setInstructions ( recipe.getInstructions ( ) );
        model.addAttribute ( "recipe" , recipe );
        //Getting average rating for recipe
        model.addAttribute ( "rating" , recipe.getAverageRating ( ) );
        //Getting the comments for the recipe using ID
        List<Comments> comments = recipeService.getComments ( recipeID );
        model.addAttribute ( "comments" , comments );
        //Creating a new comment attribute to populate with data
        model.addAttribute ( "newComment" , new Comments ( ) );
        return "recipe";
    }
    
    @PostMapping("/rate")
    public String rate (@ModelAttribute Ratings rating , @RequestParam int stars ,
                        @RequestParam("recipeID") int recipeID) {
        recipeService.saveRating ( rating );
        RecipeModel recipe = recipeService.getRecipeById ( recipeID );
        recipe.setRatingCounter ( recipe.getRatingCounter ( ) + 1 );
        recipe.setAverageRating ( (recipe.getAverageRating ( ) + stars) / recipe.getRatingCounter ( ) );
        
        return "redirect:/recipe/" + stars;
    }
    
    /**
     * Post mapping for adding a favorite in the recipe detail page.
     * Error handling for a user that's not logged in and a user that has
     * already favorited the item.
     *
     * @param recipeID           Recipe ID of that page
     * @param redirectAttributes Attributes given when redirected that gives an error message
     * @param session            Session that gives userID
     * @return Redirects user to either login or same page user was on
     */
    @PostMapping("/addFavorite")
    public String addFavorite (@RequestParam("recipeID") int recipeID ,
                               RedirectAttributes redirectAttributes ,
                               HttpSession session) {
        Integer userID = (Integer) session.getAttribute ( "userID" );
        if (userID == null)
            return "redirect:/login";
        
        boolean alreadyFavorited = recipeService.isAlreadyFavorited ( userID , recipeID );
        if (alreadyFavorited) {
            redirectAttributes.addFlashAttribute ( "message" , "This recipe is already in your favorites" );
            return "redirect:/recipe?id=" + recipeID;
        }
        
        recipeService.saveFavorite ( userID , recipeID );
        redirectAttributes.addFlashAttribute ( "message" , "You have successfully added favorite" );
        return "redirect:/recipe?id=" + recipeID;
    }
    
    /**
     * Post mapping to save a comment to the database. If user isn't logged in, they will be redirected
     * to the login page. Otherwise, user ID and username will be given to comment object and comment will
     * be saved to database
     *
     * @param comment  Model attribute, comment ojeect
     * @param recipeID ID for specific recipe page so comments aren't showing up on multiple recipe pages
     * @return Reloading the page
     */
    @PostMapping("/saveComment")
    public String saveComment (@ModelAttribute Comments comment , @RequestParam("recipeID") int recipeID ,
                               HttpSession session) {
        //commentID and userID are not being resolved in the html file
        //Using integer for the possible null exception
        Integer userID = (Integer) session.getAttribute ( "userID" );
        
        //If userID is not null then get the username from the ID
        if (userID == null)
            //Otherwise, redirect user to login
            return "redirect:/login";
        
        comment.setUserID ( userID );
        comment.setUsername ( userService.getUser ( userID ).getUsername ( ) );
        
        recipeService.saveComment ( comment );
        return "redirect:/recipe?id=" + recipeID;
    }
    
    /**
     * Add recipe page using spring boot mapping
     *
     * @param model Gets a model to add recipe model to
     * @return Returns the path of where the user is being taken
     */
    @GetMapping("/addRecipe")
    public String addRecipe (Model model) {
        model.addAttribute ( "recipe" , new RecipeModel ( ) );
        return "addRecipe";
    }

    /*NEEDED FOR IMAGE ENCODING
      @RequestParam("image") MultipartFile image,*/
    
    /**
     * Post mapping for the recipe. This is used to save the recipe, moving through the layers closer to
     * the database
     *
     * @param recipe Taking the populated recipe model data given from the user in the forms
     * @return Redirecting the user to the browse page
     */
    @PostMapping("/addRecipe")
    public String addRecipe (@ModelAttribute RecipeModel recipe) {
        recipeService.saveRecipe ( recipe );
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
    
    
    @RestController
    public class ImageUploadController {
        
        @PostMapping("/uploadImage")
        public String uploadImage(@RequestParam("image") MultipartFile file) {
            if (file.isEmpty()) {
                return "File upload failed. No file selected.";
            }
            
            // Define the target directory
            String targetDirectory = "/Users/jamariuswilder/CS Classes/CS 380/GingieWEB/src/main/resources/static/images/";
            
            // Create the target file
            File targetFile = new File(targetDirectory + file.getOriginalFilename());
            
            try {
                // Transfer the uploaded file to the target location
                file.transferTo(targetFile);
                return "File uploaded successfully to: " + targetFile.getAbsolutePath();
            } catch (IOException e) {
                e.printStackTrace();
                return "File upload failed: " + e.getMessage();
            }
        }
    }
    
    /**
     * Add user page using spring boot mapping
     *
     * @param model Gets a model to add User model to
     * @return Returns the path of where the user is being taken
     */
    @GetMapping("/register")
    public String register (Model model) {
        model.addAttribute ( "user" , new Users ( ) );
        return "register";
    }
    
    /**
     * Post mapping for the register. This is used to add a user to the database
     *
     * @param user taking the populated Users model
     * @return Redirecting the user to the login page
     */
    @PostMapping("/register")
    public String register (@ModelAttribute Users user) {
        userService.addUser ( user );
        return "redirect:/login";
    }
    
    @GetMapping("/favorites")
    public String favorites (HttpSession session ,
                             Model model) {
        Integer userID = (Integer) session.getAttribute ( "userID" );
        if (userID == null)
            return "redirect:/login";
        
        List<Favorites> favorites = recipeService.getFavoritesByUserID ( userID );
        model.addAttribute ( "favorites" , favorites );
        
        List<RecipeModel> recipes = new ArrayList<> ( );
        
        for (Favorites favorite : favorites) {
            RecipeModel recipe = recipeService.getRecipeById ( favorite.getRecipeID ( ) );
            if (recipe != null) {
                recipes.add ( recipe );
            }
        }
        
        model.addAttribute ( "recipes" , recipes );
        return "favorites";
    }
    
    /**
     * login page using spring boot mapping
     *
     * @param model Gets a model to add Users model to
     * @return Returns the path of where the user is being taken
     */
    @GetMapping("/login")
    public String login (Model model) {
        model.addAttribute ( "user" , new Users ( ) );
        return "login";
    }
    
    /**
     * Post mapping for the login. This is used to allow the user to login to their profile found in the database
     *
     * @param username username of the user
     * @param password password of the user
     * @param session  HttpSession to save the current logged-in user to
     * @return returns the path of where the user is being taken, in this case to the user profile page
     */
    @PostMapping("/login")
    public String login (@RequestParam String username ,
                         @RequestParam String password ,
                         RedirectAttributes redirectAttributes ,
                         HttpSession session , Model model) {
        //check user is in database
        Users user = userService.validateUser ( username , password );
        //If nothing was returned from login because user entered wrong credentials,
        //Login error is created and projected onto the view
        if (user == null) {
            redirectAttributes.addFlashAttribute ( "loginError" , "Invalid credentials, please try again" );
            return "redirect:/login";
        }
        
        //add userID to session attribute userID.
        //userID attribute shows the current logged-in user for the session
        session.setAttribute ( "userID" , user.getUserID ( ) );
        return "redirect:/userProfile";
    }
    
    /**
     * user profile page using spring boot mapping
     *
     * @param model   Gets a Model to add the Users model to
     * @param session HttpSession to save the current logged-in user to
     * @return either the no current user is logged in page, or the user profile page
     */
    @GetMapping("/userProfile")
    public String userProfile (Model model , HttpSession session) {
        int userID;
        //try to get the sessions userID attribute
        try {
            userID = (int) session.getAttribute ( "userID" );
        } catch (NullPointerException e) { //userID attribute is null, no one is logged in
            return "noLogIn";
        }
        //get the logged-in user's details from the database
        Users user = userService.getUser ( userID );
        model.addAttribute ( "user" , user );
        return "userProfile";
    }
    
    /**
     * mapping to logout
     *
     * @param session HttpSession that has the current logged-in user
     * @return redirect to userProfile page
     */
    @GetMapping("/logout")
    public String userProfile (HttpSession session) {
        //set sessions userID to null
        session.setAttribute ( "userID" , null );
        return "redirect:/userProfile";
    }
    
    @GetMapping("/browseBreakfast")
    public String browseBreakfast (Model model) {
        List<RecipeModel> recipes = recipeService.getRecipeByCategory ( "Breakfast" );
        model.addAttribute ( "recipes" , recipes );
        return "browse";
    }
    
    @GetMapping("/browseLunch")
    public String browseLunch (Model model) {
        List<RecipeModel> recipes = recipeService.getRecipeByCategory ( "Lunch" );
        model.addAttribute ( "recipes" , recipes );
        return "browse";
    }
    
    @GetMapping("/browseDinner")
    public String browseDinner (Model model) {
        List<RecipeModel> recipes = recipeService.getRecipeByCategory ( "Dinner" );
        model.addAttribute ( "recipes" , recipes );
        return "browse";
    }
    
    @GetMapping("/browseDesert")
    public String browseDesert (Model model) {
        List<RecipeModel> recipes = recipeService.getRecipeByCategory ( "Desert" );
        model.addAttribute ( "recipes" , recipes );
        return "browse";
    }
    
    @GetMapping("/browseSnacks")
    public String browseSnacks (Model model) {
        List<RecipeModel> recipes = recipeService.getRecipeByCategory ( "Snacks" );
        model.addAttribute ( "recipes" , recipes );
        return "browse";
    }
    
    @GetMapping("/browseDrinks")
    public String browseDrinks (Model model) {
        List<RecipeModel> recipes = recipeService.getRecipeByCategory ( "Drinks" );
        model.addAttribute ( "recipes" , recipes );
        return "browse";
    }
    
    
    @GetMapping("/browse")
    public String browse (Model model) {
        List<RecipeModel> recipes = recipeService.getAllRecipes ( );
        model.addAttribute ( "recipes" , recipes );
        return "browse";
    }
    
    // Add a new recipe with an image
    @PostMapping("/add")
    public String addRecipe (@RequestParam("dishName") String dishName ,
                             @RequestParam("category") String category ,
                             @RequestParam("image") MultipartFile image ,
                             @RequestParam("ingredients") String[] ingredients ,
                             @RequestParam("instructions") String[] instructions) {
        try {
            RecipeModel recipe = new RecipeModel ( );
            recipe.setDishName ( dishName );
            recipe.setCategoryID ( category );
            recipe.setIngredients ( java.util.List.of ( ingredients ) );
            recipe.setInstructions ( java.util.List.of ( instructions ) );
            
            File imageFile = null;
            if (image != null && ! image.isEmpty ( )) {
                String imagesDir = "src/main/resources/static/images";
                File dir = new File ( imagesDir );
                if (! dir.exists ( )) dir.mkdirs ( ); // Ensure directory exists
                
                imageFile = new File ( imagesDir + image.getOriginalFilename ( ) );
                image.transferTo ( imageFile ); // Save the uploaded image
                recipe.setimagePath ( "/images/" + image.getOriginalFilename ( ) );
            }
            
            recipeService.addRecipe ( recipe );
            return "Recipe added successfully!";
        } catch (IOException e) {
            e.printStackTrace ( );
            return "Error adding recipe.";
        }
    }
    
    // Retrieve all recipes
    @GetMapping("/all")
    public List<RecipeModel> getAllRecipes() {
        return recipeService.getAllRecipes();
    }
}
