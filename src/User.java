import java.util.ArrayList;
import java.util.List;

public class User {
	private int userID;
	private String username;
	private String password;
	private String email;
	
	public User(int userID, String username, String password, String email) {
		this.userID = userID;
		this.username = username;
		this.password = password;
		this.email = email;
	}
	
	public void addFavorite(int recipeID) {
		// Add recipe to user's favorites
	}
	
	public void addComment(String commentText, int recipeID) {
		// Add a comment to a recipe
	}
	
	public void rateRecipe(int recipeID, int stars) {
		// Rate a recipe
	}
	
	public List<Integer> getFavorites() {
		// Return the list of favorite recipe IDs
		return new ArrayList<>();
	}
	
	// Getters and Setters
	public int getUserID() {
		return userID;
	}
	
	public void setUserID(int userID) {
		this.userID = userID;
	}
	
	public String getUsername() {
		return username;
	}
	
	public void setUsername(String username) {
		this.username = username;
	}
	
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
}

class Favorites {
	private int userID;
	private final List<Integer> recipeIDs;
	
	public Favorites(int userID) {
		this.userID = userID;
		this.recipeIDs = new ArrayList<>();
	}
	
	public void addRecipe(int recipeID) {
		recipeIDs.add(recipeID);
	}
	
	public List<Integer> getRecipes() {
		return recipeIDs;
	}
	
	// Getters and Setters
	public int getUserID() {
		return userID;
	}
	
	public void setUserID(int userID) {
		this.userID = userID;
	}
}