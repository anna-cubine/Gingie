package services;

import models.Users;
import org.springframework.stereotype.Repository;
import repositories.UserRepository;

import java.util.List;

@Repository
public class UserService {
    private final UserRepository userRepository;

    public UserService( UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    /**
     * Saving user from the view and main controller down to the repository
     * where it will be stored in the database
     * @param user Taking the populated Users model
     */
    public void addUser(Users user) {
        userRepository.addUser(user);
    }

    /**
     * checking that user is in the database. used when someone is trying to log in
     * @param username username of user profile
     * @param password password of the user profile
     * @return calls userRepository which accesses the database
     */
    public Users validateUser(String username, String password) {
        return userRepository.validateUser(username, password);
    }

    /**
     * get a user from the database using userID
     * @param userID ID of user to search for
     * @return calls userRepository which accesses the database
     */
    public Users getUser(int userID) {
        return userRepository.getUser(userID);
    }
}
