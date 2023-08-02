package com.volsync.volsyncproject.controller;

import com.volsync.volsyncproject.model.User;
import com.volsync.volsyncproject.model.Volunteer;
import com.volsync.volsyncproject.service.UserService;
import com.volsync.volsyncproject.service.VolunteerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Handles HTTP requests involving the volunteer table in our database
 */
@RestController
@RequestMapping("api/v1/volunteers")
public class VolunteerController {

    // reference to service layer
    private final VolunteerService volunteerService;

    /**
     * Dependency injection for volunteerService
     * @param volunteerService service layer to interact with volunteer table in database
     */
    @Autowired
    public VolunteerController(VolunteerService volunteerService) {
        this.volunteerService = volunteerService;
    }

    /**
     * Creates a volunteer within the database
     * @param volunteer the volunteer to create
     * @return a ResponseEntity corresponding to the successfully created volunteer
     */
    @PostMapping
    public ResponseEntity<Volunteer> createVolunteer(@RequestBody Volunteer volunteer) {
        Volunteer createdVolunteer = volunteerService.createVolunteer(volunteer);

        return new ResponseEntity<Volunteer>(createdVolunteer, HttpStatus.CREATED);
    }

    /**
     * Assigns to a volunteer its corresponding user account (user accounts are used to authenticate volunteer)
     * @param volunteerId the id of the volunteer we are assigning the user account to
     * @param userId the id of the user entry in the users table that corresponds to volunteer with id = volunteerId
     * @return a ResponseEntity corresponding to the volunteer which has just been assigned a user account
     */
    @PutMapping("/{volunteerId}/users/{userId}")
    public HttpStatus assignUserToVolunteer(@PathVariable Long volunteerId, @PathVariable Long userId) {
        volunteerService.assignUserToVolunteer(volunteerId, userId);

        return HttpStatus.NO_CONTENT;
    }


}
