package repositories;

import models.Users;
import org.springframework.dao.EmptyResultDataAccessException;
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
     * Getting a user from the database based using SQL query and jdbcTemplate
     * @param userID id of user to search for
     * @return The query along with mapped Users model
     */
    public Users getUser(int userID) {
        String query = "SELECT * FROM Users where userID = ?";
        return jdbcTemplate.queryForObject(query, userMapper(), userID);
    }

    /**
     * validates user is in the database when a user attempts to log in
     * @param username username of user profile
     * @param password password of user profile
     * @return the query with mapped Users model
     */
    public Users validateUser(String username, String password) {
        String query = "SELECT * FROM Users WHERE username = ? and password = ?";
        try {
            return jdbcTemplate.queryForObject(query, userMapper(), username, password);
        } catch (EmptyResultDataAccessException e) {
            //Adding authentication handling
            return null;
        }
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
