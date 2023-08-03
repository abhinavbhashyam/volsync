package com.volsync.volsyncproject.controller;

import com.volsync.volsyncproject.model.User;
import com.volsync.volsyncproject.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


/**
 * Handles requests involving User table in database
 */
@RestController
@RequestMapping("/api/v1/users")
public class UserController {

    // reference to our service layer for users
    private final UserService userService;

    /**
     * Dependency injection for userService
     *
     * @param userService instance of our service class
     */
    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }


    /**
     * Handles all requests to create a User
     *
     * @param user the user we want to create
     * @return a ResponseEntity corresponding to the user that was created as well as CREATED http status
     */
    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody User user) {
        // create the user using the service
        User createdUser = userService.createUser(user);

        // return the created user along with CREATED status
        return new ResponseEntity<User>(createdUser, HttpStatus.CREATED);
    }

    /**
     * Get a user from the databse based on their id
     * @param userId the id of the user we want
     * @return a ResponseEntity corresponding to the user we wanted to get
     */
    @GetMapping("/{userId}")
    public ResponseEntity<User> getUserById(@PathVariable Long userId) {
        User user = userService.getUserById(userId);

        return new ResponseEntity<User>(user, HttpStatus.OK);
    }
}