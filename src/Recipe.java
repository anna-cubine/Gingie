import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Recipe {
	private final int recipeID;
	private final String name;
	private final List<String> ingredients;
	private final List<String> instructions;
	private final int categoryID;
	private double averageRating;
	
	public Recipe(int recipeID, String name, List<String> ingredients, List<String> instructions, int categoryID) {
		this.recipeID = recipeID;
		this.name = name;
		this.ingredients = ingredients;
		this.instructions = instructions;
		this.categoryID = categoryID;
		this.averageRating = 0;
	}
	
	public void updateAverageRating(double newRating) {
		this.averageRating = newRating;
	}
}

class Category {
	private final int categoryID;
	private final String categoryName;
	
	public Category(int categoryID, String categoryName) {
		this.categoryID = categoryID;
		this.categoryName = categoryName;
	}
}

class Comment {
	private final int commentID;
	private final int userID;
	private final int recipeID;
	private final String commentText;
	private final LocalDateTime timestamp;
	
	public Comment(int commentID, int userID, int recipeID, String commentText) {
		this.commentID = commentID;
		this.userID = userID;
		this.recipeID = recipeID;
		this.commentText = commentText;
		this.timestamp = LocalDateTime.now();
	}
}

class Rating {
	private final int ratingID;
	private final int recipeID;
	private final int userID;
	private final int stars;
	private final LocalDateTime timestamp;
	
	public Rating(int ratingID, int recipeID, int userID, int stars) {
		this.ratingID = ratingID;
		this.recipeID = recipeID;
		this.userID = userID;
		this.stars = stars;
		this.timestamp = LocalDateTime.now();
	}
}