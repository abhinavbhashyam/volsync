package com.volsync.volsyncproject.service;

import com.volsync.volsyncproject.exception.ResourceNotFoundException;
import com.volsync.volsyncproject.model.Organization;
import com.volsync.volsyncproject.model.User;
import com.volsync.volsyncproject.model.Volunteer;
import com.volsync.volsyncproject.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Handles all interaction with the UserRepository
 */
@Service
public class UserService {

    // reference to the user repository later
    private final UserRepository userRepository;

    /**
     * Dependency injection for userRepository
     * @param userRepository instance of our repository class
     */
    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    /**
     * Create the given user by saving it to the database
     * @param user the user to create
     * @return the user that was added to the database
     */
    public User createUser(User user) {
        return userRepository.save(user);

    }

}
