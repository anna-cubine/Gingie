import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Recipe {
	private int recipeID;
	private String name;
	private List<String> ingredients;
	private List<String> instructions;
	private int categoryID;
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
	private int categoryID;
	private String categoryName;
	
	public Category(int categoryID, String categoryName) {
		this.categoryID = categoryID;
		this.categoryName = categoryName;
	}
}

class Comment {
	private int commentID;
	private int userID;
	private int recipeID;
	private String commentText;
	private LocalDateTime timestamp;
	
	public Comment(int commentID, int userID, int recipeID, String commentText) {
		this.commentID = commentID;
		this.userID = userID;
		this.recipeID = recipeID;
		this.commentText = commentText;
		this.timestamp = LocalDateTime.now();
	}
}

class Rating {
	private int ratingID;
	private int recipeID;
	private int userID;
	private int stars;
	private LocalDateTime timestamp;
	
	public Rating(int ratingID, int recipeID, int userID, int stars) {
		this.ratingID = ratingID;
		this.recipeID = recipeID;
		this.userID = userID;
		this.stars = stars;
		this.timestamp = LocalDateTime.now();
	}
}