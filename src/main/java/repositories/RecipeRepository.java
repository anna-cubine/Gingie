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

    public void saveRecipe(RecipeModel recipe) {
        String query = "INSERT INTO gingie (recipeID, name, ingredients, instructions, categoryID, averageRating) VALUES (?,?,?,?,?,?) ";
        jdbcTemplate.update(query, recipe.getRecipeID(), recipe.getDishName(), recipe.getIngredients(), recipe.getInstructions(), recipe.getCategoryID(), recipe.getAverageRating());
    }

    public List<RecipeModel> getAllRecipes() {
        String query = "SELECT * FROM gingie ";
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
