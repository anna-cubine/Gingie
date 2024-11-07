package services;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RecipeDatabase {
    public static Connection con = null;

    public Connection connect() {
        // Connect to the database
        String url = "jdbc:mysql://75.253.5.172:36750/gingie";
        String username = "jam";
        String password = "Sql3396!";

        // Create a connection
        try{
            con = DriverManager.getConnection(url, username, password);
            System.out.println("Connected successfully");
        }catch(Exception e) {
            System.out.println ( "exception: " + e.getMessage ( ) );
        }
        return con;
    }
    /*
    public List<Recipe> queryRecipes(String keyword) {
        return new ArrayList<>();
    }*/

    /**
     * Add recipe to the database
     *
     * @param recipeID Unique ID for each recipe
     * @param name Dish name
     * @param ingredients Ingredients, will have to format before sending to this recipe
     * @param instructions Recipe directions, same as ingredients
     * @param categoryID Each recipe will be delegated to one category
     * @param averageRating Recipe will be given an average star rating
     */
    public void addRecipe(String recipeID, String name, String ingredients, String instructions, String categoryID, String averageRating) throws SQLException {
        String query = "INSERT INTO table_name(recipeID, name, ingredients, indtructions, categoryID, averageRating" +
                "VALUES(?, ?, ?, ?, ?, ?);";

        //Connect to database first
        connect();

        PreparedStatement prepareStat = con.prepareStatement(query);

        try{
            prepareStat.setString(1, recipeID);
            prepareStat.setString(2, name);
            prepareStat.setString(3, ingredients);
            prepareStat.setString(4, instructions);
            prepareStat.setString(5, categoryID);
            prepareStat.setString(6, averageRating);
        } catch(SQLException e){
            System.out.println ( "exception: " + e.getMessage());
        }
        prepareStat.execute();
    }

    public void update(int id, Object data) {
        // Update data in the database
    }

    public void delete(int id) {
        // Delete data from the database
    }
}
