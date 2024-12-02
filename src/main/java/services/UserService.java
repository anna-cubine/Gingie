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

    public Users getUser(int userID) {
        return userRepository.getUser(userID);
    }
}
