package com.volsync.volsyncproject.service;

import com.volsync.volsyncproject.auth_user_details.CustomUserDetails;
import com.volsync.volsyncproject.model.User;
import com.volsync.volsyncproject.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * Service layer to give us information about the user we are trying to authenticate
 */
@Service
public class CustomUserDetailsService implements UserDetailsService {

    // reference to repository layer
    private final UserRepository userRepository;

    /**
     * Dependency injection for userRepository
     * @param userRepository reference to repository layer
     */
    @Autowired
    public CustomUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    /**
     * Fetches a user by username from the database
     * @param username the username of the user we are trying to get from the database
     * @return a UserDetails object that corresponds to the user we are trying to authenticate
     * @throws UsernameNotFoundException if username doesn't exist in the database
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // fetch user with this username from the database
        User user = userRepository.findByUsername(username);

        // in case the user was not found
        if (user == null) {
            throw new UsernameNotFoundException("User not found");
        }

        // if the user was found, create a custom user details wrapper
        return new CustomUserDetails(user);
    }
}
