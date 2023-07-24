package com.volsync.volsyncproject.controller;

import com.volsync.volsyncproject.model.Organization;
import com.volsync.volsyncproject.model.User;
import com.volsync.volsyncproject.model.Volunteer;
import com.volsync.volsyncproject.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Handles requests involving User entity (mainly for login/registration)
 */
@RestController
@RequestMapping("/api/v1/users")    // handles requests of this format
public class UserController {

    // reference to our service layer for users
    private final UserService userService;

    /**
     * Dependency injection for userService
     * @param userService instance of our service class
     */
    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }


    /**
     * Handles all requests to create a User
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
     * Handles all requests to sync a user account and a volunteer (initialize foreign key references for one-to-one mapping)
     * @param volunteer the volunteer data that corresponds to the user with id = id (the id passed into the method)
     * @param userId the id of the user we are syncing the volunteer with
     */
    @PutMapping("/volunteers/{userId}")
    public ResponseEntity<Volunteer> syncUserAccountAndVolunteer(@RequestBody Volunteer volunteer, @PathVariable Long userId) {

        if ()
        userService.createVolunteerAndAssignUser(volunteer, id);
    }

    @PutMapping("/organizations/{id}")
    void createOrganization(@RequestBody Organization organization, @PathVariable Long id) {
        userService.createOrganizationAndAssignUser(organization, id);
    }



}