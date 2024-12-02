package models;

public class Comments {
    private int commentID;
    private int userID;
    private int recipeID;
    private String commentText;
    private String timestamp;

    public Comments(int commentID, int userID, int recipeID, String commentText, String timestamp) {
        this.commentID = 1;
        this.userID = userID;
        this.recipeID = recipeID;
        this.commentText = commentText;
        this.timestamp = timestamp;
    }

    public int getCommentID() { return commentID; }
    public int getUserID() { return userID; }
    public int getRecipeID() { return recipeID; }
    public String getCommentText() { return commentText; }
    public String getTimestamp() { return timestamp; }

    public void setCommentID(int commentID) { this.commentID = commentID; }
    public void setUserID(int userID) { this.userID = userID; }
    public void setRecipeID(int recipeID) { this.recipeID = recipeID; }
    public void setCommentText(String commentText) { this.commentText = commentText; }
    public void setTimestamp(String timestamp) { this.timestamp = timestamp; }
}
