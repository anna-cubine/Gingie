package services;

import models.Comments;
import models.RecipeModel;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import repositories.RecipeRepository;

import java.util.List;

@Service
public class RecipeService {
    private final RecipeRepository recipeRepository;

    /**
     * Constructor for recipe service
     * @param recipeRepository The repository for recipes
     */
    public RecipeService( RecipeRepository recipeRepository) {
        this.recipeRepository = recipeRepository;
    }

    /**
     * Getting all recipes from the repository
     * @return List of recipe models mapped properly
     */
    public List<RecipeModel> getAllRecipes() {
        return recipeRepository.getAllRecipes();
    }

    /**
     * Getting recipe from repository based on ID
     * @param id Recipe ID for given recipe
     * @return One recipe model mapped to ID specs
     */
    public RecipeModel getRecipeById(int id) {
        return recipeRepository.getRecipeById(id);
    }

    /**
     * Gets any recipes models that have the specified category ID
     * @param category Category string (ie breakfast, lunch etc...)
     * @return List of recipes that correspond to that category ID
     */
    public List<RecipeModel> getRecipeByCategory(String category) {
        return recipeRepository.getRecipeByCategoryID(category);
    }

    /**
     * Gets any comment that have specified recipe ID
     * @param id Recipe ID given from the recipe page
     * @return All comments mapped to that recipe ID
     */
    public List<Comments> getComments(int id) {
        return recipeRepository.getComments(id);
    }

    public void saveComment(Comments comment) {
    }

    /**
     * Saving recipe from the view and main controller down to the repository
     * where it will be stored in the database
     * @param recipeModel Taking the populated recipe model
     */
    public void saveRecipe(RecipeModel recipeModel) {
        recipeRepository.saveRecipe(recipeModel);
    }
}
