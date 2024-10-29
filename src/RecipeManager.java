import java.util.ArrayList;
import java.util.List;

public class RecipeManager {
	private final Database database;
	
	public RecipeManager(Database database) {
		this.database = database;
	}
	
	public List<Recipe> searchRecipes(String keyword) {
		return database.queryRecipes(keyword);
	}
	
	public void addRecipe(Recipe recipe) {
		database.save(recipe);
	}
	
	public void editRecipe(int recipeID, Recipe updatedRecipe) {
		database.update(recipeID, updatedRecipe);
	}
	
	public void deleteRecipe(int recipeID) {
		database.delete(recipeID);
	}
}

class Database {
	public void connect() {
		// Connect to the database
	}
	
	public List<Recipe> queryRecipes(String keyword) {
		return new ArrayList<>();
	}
	
	public void save(Object data) {
		// Save data to the database
	}
	
	public void update(int id, Object data) {
		// Update data in the database
	}
	
	public void delete(int id) {
		// Delete data from the database
	}
}