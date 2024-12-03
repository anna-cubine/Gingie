package services;

import models.Comments;
import models.Favorites;
import models.Ratings;
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
    /*
    public List<RecipeModel> getRecipesByID(int recipeID) {
        return recipeRepository.getRecipesByID(recipeID);
    }*/

    /**
     * Checking if recipe is already favorited using userID and recipeID
     * @param userID currect user
     * @param recipeID recipe page user is on
     * @return boolean of whether the recipe is already favorited or not
     */
    public boolean isAlreadyFavorited(int userID, int recipeID) {
        return recipeRepository.isAlreadyFavorite(userID, recipeID);
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
     * Saving rating to database
     * @param rating Given rating for current recipe page user is on
     */
    public void saveRating(Ratings rating) {
        recipeRepository.saveRating(rating);
    }

    /**
     * Saving favorite to database
     * @param userID current user
     * @param recipeID current recipe page
     */
    public void saveFavorite(int userID, int recipeID) {
        recipeRepository.saveFavorite(userID, recipeID);
    }

    /**
     * Getting list of favorites from the userID
     * @param userID current logged in user
     * @return List of favorites
     */
    public List<Favorites> getFavoritesByUserID(int userID) {
        return recipeRepository.getFavoritesByUserID(userID);
    }

    /**
     * Gets any comment that have specified recipe ID
     * @param id Recipe ID given from the recipe page
     * @return All comments mapped to that recipe ID
     */
    public List<Comments> getComments(int id) {
        return recipeRepository.getComments(id);
    }

    /**
     * Method to save comment, bridging between controller and repository
     * @param comment Comment with populated data
     */
    public void saveComment(Comments comment) {
        recipeRepository.saveComment(comment);
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
