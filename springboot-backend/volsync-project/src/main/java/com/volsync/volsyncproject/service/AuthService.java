package com.volsync.volsyncproject.service;

import com.volsync.volsyncproject.auth_user_details.CustomUserDetails;
import com.volsync.volsyncproject.model.User;
import com.volsync.volsyncproject.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * Service class to assist us with user authentication
 */
@Service
public class AuthService {
    // reference to user repository later
    public final UserRepository userRepository;

    /**
     * Dependency injection for user repository
     * @param userRepository reference to user repository
     */
    @Autowired
    public AuthService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    /**
     * Fetches the currently logged in user
     * @return the currently logged in user
     */
    public User getLoggedInUser() {
        // get logged in user username
        String loggedInUserUsername =
                ((CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUsername();

        // find user by username in database
        User loggedInUser = userRepository.findByUsername(loggedInUserUsername);

        if (loggedInUser == null) {
            throw new UsernameNotFoundException("Username not found: " + loggedInUserUsername);
        } else {
            return loggedInUser;
        }

    }
}
