package com.volsync.volsyncproject.service;

import com.volsync.volsyncproject.exception.ResourceNotFoundException;
import com.volsync.volsyncproject.model.User;
import com.volsync.volsyncproject.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Service layer to help us fetch information about the currently signed in user
 */
@Service
public class AuthService {

    // reference to user repository layer
    private final UserRepository userRepository;

    /**
     * Dependency injection for user repository
     * @param userRepository reference to user repository layer
     */
    @Autowired
    public AuthService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    /**
     * Retrieves the user object corresponding to user with username = given username
     * @param username the given username we are trying to get the user object for
     * @return the user object corresponding to username = given username
     */
    public User findUserByUsername(String username) {
        // find the user in the database
        User user = userRepository.findByUsername(username);

        // if no user with this username, throw exception
        if (user == null) {
            throw new ResourceNotFoundException("User doesn't exist with username: " + username);
        }

        // return the end user
        return user;
    }
}
