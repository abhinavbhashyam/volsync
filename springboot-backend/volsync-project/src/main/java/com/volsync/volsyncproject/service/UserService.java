package com.volsync.volsyncproject.service;

import com.volsync.volsyncproject.model.User;
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
    public User createUser(User user) throws SQLIntegrityConstraintViolationException {
        // encode the user's password in the database using bcrypt
        String encodedPassword = this.passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);
        return userRepository.save(user);   // can throw exception if there is a duplicate username

    }

}
