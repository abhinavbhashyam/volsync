package com.volsync.volsyncproject.service;

import com.volsync.volsyncproject.exception.DuplicateUsernameException;
import com.volsync.volsyncproject.exception.ResourceNotFoundException;
import com.volsync.volsyncproject.model.Organization;
import com.volsync.volsyncproject.model.User;
import com.volsync.volsyncproject.model.Volunteer;
import com.volsync.volsyncproject.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.sql.SQLIntegrityConstraintViolationException;

/**
 * Handles all interaction with the UserRepository
 */
@Service
public class UserService {

    // reference to the user repository layer
    private final UserRepository userRepository;

    // to encode user password (BCrypt)
    private final PasswordEncoder passwordEncoder;

    /**
     * Dependency injection for userRepository, and also initialize password encoder
     *
     * @param userRepository  instance of our repository class
     */
    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
        this.passwordEncoder = new BCryptPasswordEncoder();
    }

    /**
     * Create the given user by saving it to the database
     * @param user the user to create
     * @return the user that was added to the database
     */
    public void createUser(User user) {
        // encode the user's password in the database using bcrypt
        String encodedPassword = this.passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);

        try {
            userRepository.save(user);   // can throw exception if there is a duplicate username
        } catch (Exception exception) {
            // the reason I need to catch general Exception here is b/c save method doesn't throw
            // SQLIntegrityConstraintViolationException, so I can't catch that exception here
            throw new DuplicateUsernameException("Username already exists: " + user.getUsername());
        }
    }
    public User getUserById(Long userId) {
        User desiredUser = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + userId));

        return desiredUser;
    }
}
