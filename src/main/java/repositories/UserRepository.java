package repositories;

import models.Users;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserRepository {

    private final JdbcTemplate jdbcTemplate;

    public UserRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    /**
     * Adding user to database using SQL query and jdbcTemplate
     * @param user Taking in a Users model to add to database
     */
    public void addUser(Users user) {
        String query = "INSERT INTO Users (userID, username, password, email) VALUES (?,?,?,?)";
        jdbcTemplate.update(query,
                user.getUserID(),
                user.getUsername(),
                user.getPassword(),
                user.getEmail()
                );
    }

    /**
     * Getting all recipes from the database using SQL query and jdbcTemplate
     * @return The query along with mapped recipe model.
     */
    public List<Users> findUser() {
        String query = "SELECT * FROM Users ";
        return jdbcTemplate.query(query, userMapper());
    }

    public RowMapper<Users> userMapper() {
        return (rs, rowNum) -> new Users(
                rs.getInt("UserID"),
                rs.getString("username"),
                rs.getString("password"),
                rs.getString("email")
        );
    }
}
