package models;

public class Users {
    private int userID;
    private String username;
    private String password;
    private String email;

    /**
     * Constructor for the User class
     * @param userID Unique ID for every user
     * @param username Username created by user
     * @param password Password created by user
     * @param email email given by user
     */
    public Users(int userID, String username, String password, String email) {
        this.userID = userID;
        this.username = username;
        this.password = password;
        this.email = email;
    }

    //Getters and setters
    public int getUserID() { return userID; }
    public void setUserID(int userID) {this.userID = userID; }

    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
}
