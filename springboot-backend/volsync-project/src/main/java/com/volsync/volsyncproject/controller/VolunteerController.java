package com.volsync.volsyncproject.controller;

import com.volsync.volsyncproject.model.User;
import com.volsync.volsyncproject.model.Volunteer;
import com.volsync.volsyncproject.service.UserService;
import com.volsync.volsyncproject.service.VolunteerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/volunteers")
public class VolunteerController {

    private final VolunteerService volunteerService;

    @Autowired
    public VolunteerController(VolunteerService volunteerService) {
        this.volunteerService = volunteerService;
    }

    @PostMapping
    public ResponseEntity<Volunteer> createVolunteer(@RequestBody Volunteer volunteer) {
        Volunteer createdVolunteer = volunteerService.createVolunteer(volunteer);

        return new ResponseEntity<Volunteer>(createdVolunteer, HttpStatus.CREATED);
    }

    @PutMapping("/{volunteerId}/users/{userId}")
    public ResponseEntity<Volunteer> assignUserToVolunteer(@PathVariable Long volunteerId, @PathVariable Long userId) {
        Volunteer assignedVolunteer = volunteerService.assignUserToVolunteer(volunteerId, userId);

        return new ResponseEntity<Volunteer>(assignedVolunteer, HttpStatus.OK);
    }
}
