package services;

import models.RecipeModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import repositories.RecipeRepository;

import java.util.List;

@Repository
public class RecipeService {
    private final RecipeRepository recipeRepository;

    //@Autowired
    public RecipeService( RecipeRepository recipeRepository) {
        this.recipeRepository = recipeRepository;
    }

    public List<RecipeModel> getAllRecipes() {
        return recipeRepository.getAllRecipes();
    }

    /**
     * Saving recipe from the view and main controller down to the repository
     * where it will be stored in the database
     * @param recipeModel Taking the populated recipe model
     */
    public void saveRecipe(RecipeModel recipeModel) {
        recipeRepository.saveRecipe(recipeModel);
    }










    /*
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

    public List<Recipe> queryRecipes(String keyword) {
        return new ArrayList<>();
    }

    public void addRecipe(String recipeID, String name, String ingredients, String instructions, String categoryID, String averageRating) throws SQLException {
        String query = "INSERT INTO table_name(recipeID, name, ingredients, instructions, categoryID, averageRating" +
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
        Update data in the database
    }

    public void delete(int id) {
        Delete data from the database
    }*/
}
