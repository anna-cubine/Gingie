package models;

public class Comments {
    private int commentID;
    private int userID;
    private int recipeID;
    private String commentText;
    private String timestamp;
    private String username;

    //Empty constructor for new comment
    public Comments() {
    }

    public Comments(int commentID, int userID, int recipeID, String commentText, String timestamp, String username) {
        this.commentID = commentID;
        this.userID = userID;
        this.recipeID = recipeID;
        this.commentText = commentText;
        this.timestamp = timestamp;
        this.username = username;
    }

    public int getCommentID() { return commentID; }
    public int getUserID() { return userID; }
    public int getRecipeID() { return recipeID; }
    public String getCommentText() { return commentText; }
    public String getTimestamp() { return timestamp; }
    public String getUsername() { return username; }

    public void setCommentID(int commentID) { this.commentID = commentID; }
    public void setUserID(int userID) { this.userID = userID; }
    public void setRecipeID(int recipeID) { this.recipeID = recipeID; }
    public void setCommentText(String commentText) { this.commentText = commentText; }
    public void setTimestamp(String timestamp) { this.timestamp = timestamp; }
    public void setUsername(String username) { this.username = username; }
}
