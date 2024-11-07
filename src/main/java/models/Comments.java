package models;

public class Comments {
    private int commentID;
    private String commentText;
    private String timestamp;

    public Comments(int commentID, String commentText, String timestamp) {
        this.commentID = commentID;
        this.commentText = commentText;
        this.timestamp = timestamp;
    }
}
