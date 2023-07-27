package com.volsync.volsyncproject.controller;

import com.volsync.volsyncproject.model.Organization;
import com.volsync.volsyncproject.model.User;
import com.volsync.volsyncproject.model.Volunteer;
import com.volsync.volsyncproject.service.UserService;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLIntegrityConstraintViolationException;

/**
 * Handles requests involving User entity
 */
@RestController
@RequestMapping("/api/v1/users")    // handles requests of this format
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
        User createdUser;

        try {
            createdUser = userService.createUser(user);
        } catch (SQLIntegrityConstraintViolationException sqlIntegrityConstraintViolationException) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }


        // return the created user along with CREATED status
        return new ResponseEntity<User>(createdUser, HttpStatus.CREATED);
    }

    @GetMapping("/{userId}/volunteer")
    public ResponseEntity<Volunteer> getVolunteerCorrespondingToUser(@PathVariable Long userId) {
        Volunteer volunteerCorrespondingToUser = userService.getVolunteerCorrespondingToUser(userId);

        return new ResponseEntity<Volunteer>(volunteerCorrespondingToUser, HttpStatus.OK);
    }

    @GetMapping("/{userId}/organization")
    public ResponseEntity<Organization> getOrganizationCorrespondingToUser(@PathVariable Long userId) {
        Organization organizationCorrespondingToUser = userService.getOrganizationCorrespondingToUser(userId);

        return new ResponseEntity<Organization>(organizationCorrespondingToUser, HttpStatus.OK);
    }
}