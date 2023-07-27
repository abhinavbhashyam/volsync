package com.volsync.volsyncproject.controller;

import com.volsync.volsyncproject.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controller to handle login requests (for volunteer and organization roles)
 */
@RestController
@RequestMapping("/api/v1/login")
public class AuthController {

    /**
     * Mapping to log in a volunteer account
     * @return a ResponseEntity corresponding to a successfully logged in volunteer
     */
    @GetMapping("/volunteer")
    public ResponseEntity<String> volunteerPage() {
        return new ResponseEntity<String>("VOL", HttpStatus.OK);
    }

    /**
     * Mapping to log in an organization account
     * @return a ResponseEntity corresponding to a successfully logged in organization
     */
    @GetMapping("/organization")
    public ResponseEntity<String> organizationPage() {
        return new ResponseEntity<String>("ORG", HttpStatus.OK);
    }
}
