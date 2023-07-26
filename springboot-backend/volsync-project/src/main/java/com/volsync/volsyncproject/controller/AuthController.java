package com.volsync.volsyncproject.controller;

import com.volsync.volsyncproject.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Handles login sequence
 */
@RestController
@RequestMapping("/api/v1/login")
public class AuthController {

    // after the user is authenticated, we need to retrieve the user object from the database

    // note: since user is authenticated, we have their username, so we can find the user by username in the database
    // and get the vol/org that it the user corresponds to.
    @GetMapping("/volunteer")
    public ResponseEntity<String> volunteerPage() {
        return new ResponseEntity<String>("VOL", HttpStatus.OK);
    }

    @GetMapping("/organization")
    public ResponseEntity<String> organizationPage() {
        return new ResponseEntity<String>("ORG", HttpStatus.OK);
    }
}
