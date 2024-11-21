package repositories;

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
        String query = "INSERT INTO Recipes (recipeID, name, ingredients, instructions, category, user_username, averageRating) VALUES (?,?,?,?,?,?,?) ";
        jdbcTemplate.update(query,
                recipe.getRecipeID(),
                recipe.getDishName(),
                String.join("\n",recipe.getIngredients()),
                String.join("\n",recipe.getInstructions()),
                recipe.getCategoryID(),
                "test",
                0);
    }

    /**
     * Getting all recipes from the database using SQL query and jdbcTemplate
     * @return The query along with mapped recipe model.
     */
    public List<RecipeModel> getAllRecipes() {
        String query = "SELECT * FROM Recipes ";
        return jdbcTemplate.query(query, recipeMapper());
    }

    public RowMapper<RecipeModel> recipeMapper() {
        return (rs, rowNum) -> new RecipeModel(
                rs.getInt("recipeID"),
                rs.getString("name"),
                rs.getString("ingredients"),
                rs.getString("instructions"),
                rs.getInt("categoryID"),
                rs.getDouble("averageRating")
        );
    }
}
