package services;

import models.RecipeModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class RecipeService {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public RecipeService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    /**
     * Getting data from the database for the recipes. Using jdbcTemplate
     * and a sql query to bring into java
     * @return RecipeModel data
     */
    public List<RecipeModel> getRecipe() {
        String sql = "SELECT * FROM Recipes";

        return jdbcTemplate.query(sql, (rs, _) -> new RecipeModel(
                rs.getInt("recipeID"),
                rs.getString("name"),
                rs.getString("ingredients"),
                rs.getString("instructions"),
                rs.getInt("categoryID"),
                rs.getDouble("averageRating")));
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
