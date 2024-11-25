package services;

import models.RecipeModel;
import org.springframework.stereotype.Repository;
import repositories.RecipeRepository;

import java.util.List;

@Repository
public class RecipeService {
    private final RecipeRepository recipeRepository;

    public RecipeService( RecipeRepository recipeRepository) {
        this.recipeRepository = recipeRepository;
    }

    public List<RecipeModel> getAllRecipes() {
        return recipeRepository.getAllRecipes();
    }

    public RecipeModel getRecipeById(int id) {
        return recipeRepository.getRecipeById(id);
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
