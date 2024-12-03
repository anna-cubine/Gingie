package repositories;

import models.Comments;
import models.Favorites;
import models.Ratings;
import models.RecipeModel;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class RecipeRepository {
    
    private List<RecipeModel> recipes = new ArrayList<>();
    private final JdbcTemplate jdbcTemplate;

    public RecipeRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


    /**
     * Adding recipe to database using SQL query and jdbcTemplate
     * @param recipe Taking in a recipe model to add to database
     */
    public void saveRecipe(RecipeModel recipe) {
        String query = "INSERT INTO Recipes (recipeID, name, ingredients, instructions, categoryID, user_username, averageRating,imagePath) VALUES (?,?,?,?,?,?,?,?) ";
        jdbcTemplate.update(query,
                recipe.getRecipeID(),
                recipe.getDishName(),
                String.join("\n",recipe.getIngredients()),
                String.join("\n",recipe.getInstructions()),
                recipe.getCategoryID(),
                "test",
                0,
                recipe.getimagePath ());
    }

    public void saveRating(Ratings rating) {
        String query = "INSERT INTO Ratings (ratingID, userID, recipeID, stars, timestamp) VALUES (?,?, ?, ?, ?)";
        jdbcTemplate.update(query,
                rating.getRatingID(),
                rating.getUserID(),
                rating.getRecipeID(),
                rating.getStars(),
                rating.getTimestamp());
    }

    /**
     * Saving new comment to database using jdbc query
     * @param comment Comment object with data populated
     */
    public void saveComment(Comments comment) {
        String query = "INSERT INTO Comments (commentID, userID, recipeID, commentText, timestamp, username) VALUES (?, ?, ?, ?, ?, ?)";
        jdbcTemplate.update(query,
                comment.getCommentID(),
                comment.getUserID(),
                comment.getRecipeID(),
                comment.getCommentText(),
                comment.getTimestamp(),
                comment.getUsername());
    }

    /**
     * Getting all comments from database that matches the recipe ID given
     * using SQL and jdbc template
     * @param id Given recipe ID
     * @return All comments corresponding with recipe
     */
    public List<Comments> getComments(int id) {
        String query = "SELECT * FROM Comments WHERE recipeID = ?";
        return jdbcTemplate.query(query, commentsMapper(), id);
    }

    /**
     * Getting all recipes from the database using SQL query and jdbcTemplate
     * @return The query along with mapped recipe model.
     */
    public List<RecipeModel> getAllRecipes() {
        String query = "SELECT * FROM Recipes ";
        return jdbcTemplate.query(query, recipeMapper());
    }

    /**
     * Getting all recipes from the database that matches the recipe ID given
     * using SQL and jdbcTemplate
     * @param id Given recipe ID
     * @return Each Recipe Model that is taken from SQL
     */
    public RecipeModel getRecipeById(int id) {
        String query = "SELECT * FROM Recipes WHERE recipeID = ?";
        return jdbcTemplate.queryForObject(query, recipeMapper(), id);
    }

    /**
     * Saving the favorite from the recipe page
     * @param userID Current logged in user
     * @param recipeID Current recipe page
     */
    public void saveFavorite(int userID, int recipeID) {
        String query = "INSERT INTO Favorites (userID, recipeID) VALUES (?, ?)";
        jdbcTemplate.update(query, userID,
                recipeID);
    }

    /**
     * Getting all the favorites from the current user
     * @param userID current logged in user
     * @return All favorites tied to that user
     */
    public List<Favorites> getFavoritesByUserID(int userID) {
        String query = "SELECT * FROM Favorites WHERE userID = ?";
        return jdbcTemplate.query(query, favoritesMapper(), userID);
    }

    /**
     * Getting all recipes from the database that matches the category ID given
     * using SQL and jdbcTemplate
     * @param categoryID Given category ID
     * @return Each recipe that is taken from SQL
     */
    public List<RecipeModel> getRecipeByCategoryID(String categoryID) {
        String query = "SELECT * FROM Recipes WHERE categoryID = ?";
        return jdbcTemplate.query(query, recipeMapper(), categoryID);
    }

    /**
     * Checking if recipe is already favorited using user and recipe IDs. Getting the count
     * of favorites where userID and recipeID are equal to the ones given. Then, we take that and put it into
     * an integer. If that integer is not null and the count is larger than 0, return true
     * @param userID current user
     * @param recipeID current page recipe ID
     * @return whether the count is above 0 or not
     */
    public boolean isAlreadyFavorite(int userID, int recipeID) {
        String query = "SELECT COUNT(*) FROM Favorites WHERE userID = ? AND recipeID = ?";
        //Getting a count of the jdbc query
        Integer count = jdbcTemplate.queryForObject(query, Integer.class, userID, recipeID);
        //If there are any favorites, then return true
        return count != null && count > 0;
    }

    /**
     * Mapping each part of a recipe to the recipe model so I can grab it in the controller
     * @return Mapped Recipe Model
     */
    public RowMapper<RecipeModel> recipeMapper() {
        return (rs, rowNum) -> new RecipeModel(
                rs.getInt("recipeID"),
                rs.getString("name"),
                rs.getString("ingredients"),
                rs.getString("instructions"),
                rs.getString("categoryID"),
                rs.getDouble("averageRating"),
                rs.getString("imagePath")
        );
    }

    /**
     * Mapping each part of a comment to the comment model to be grabbable for controller
     * @return Mapped comment model
     */
    public RowMapper<Comments> commentsMapper() {
        return (rs, rowNum) -> new Comments(
                rs.getInt("commentID"),
                rs.getInt("userID"),
                rs.getInt("recipeID"),
                rs.getString("commentText"),
                rs.getString("timestamp"),
                rs.getString("username")
        );
    }

    /**
     * Mapper for favorites. Mapping all data
     * @return Mapped favorites
     */
    public RowMapper<Favorites> favoritesMapper() {
        return (rs, rowNum) -> new Favorites(
                rs.getInt("userID"),
                rs.getInt("recipeID")
        );
    }
    // Save a recipe to the list (simulate database save)
    public void save(RecipeModel recipe) {
        recipes.add(recipe);
        System.out.println("Saved recipe: " + recipe);
    }
    
    // Retrieve all recipes
    public List<RecipeModel> findAll() {
        return recipes;
    }
}
