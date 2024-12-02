package repositories;

import models.Comments;
import models.RecipeModel;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class RecipeRepository {

    private final JdbcTemplate jdbcTemplate;

    public RecipeRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    /**
     * Adding recipe to database using SQL query and jdbcTemplate
     * @param recipe Taking in a recipe model to add to database
     */
    public void saveRecipe(RecipeModel recipe) {
        String query = "INSERT INTO Recipes (recipeID, name, ingredients, instructions, categoryID, user_username, averageRating) VALUES (?,?,?,?,?,?,?) ";
        jdbcTemplate.update(query,
                recipe.getRecipeID(),
                recipe.getDishName(),
                String.join("\n",recipe.getIngredients()),
                String.join("\n",recipe.getInstructions()),
                recipe.getCategoryID(),
                "test",
                0);
    }

    public void saveComment(Comments comment) {
        String query = "INSERT INTO Comments (commentID, userID, recipeID, commentText, timestamp) VALUES (?, ?, ?,?,?)";
        jdbcTemplate.update(query,
                comment.getCommentID(),
                comment.getUserID(),
                comment.getRecipeID(),
                comment.getCommentText(),
                comment.getTimestamp());
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
                rs.getDouble("averageRating")
        );
    }

    public RowMapper<Comments> commentsMapper() {
        return (rs, rowNum) -> new Comments(
                rs.getInt("commentID"),
                rs.getInt("userID"),
                rs.getInt("recipeID"),
                rs.getString("commentText"),
                rs.getString("timestamp")
        );
    }
}
