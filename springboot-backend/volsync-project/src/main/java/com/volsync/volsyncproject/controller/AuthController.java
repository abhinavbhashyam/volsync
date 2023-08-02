package com.volsync.volsyncproject.controller;

import com.volsync.volsyncproject.auth_user_details.CustomUserDetails;
import com.volsync.volsyncproject.model.Organization;
import com.volsync.volsyncproject.model.User;
import com.volsync.volsyncproject.model.Volunteer;
import com.volsync.volsyncproject.service.AuthService;
import com.volsync.volsyncproject.service.UserService;
import org.apache.coyote.Response;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

/**
 * Controller to handle login requests (for volunteer and organization roles)
 */
@RestController
@RequestMapping("/api/v1/login")
public class AuthController {

    private final AuthService authService;

    @Autowired
    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    /**
     * Mapping to log in a volunteer account
     * @return a HttpStatus corresponding to a successfully logged in volunteer
     */
    @PostMapping("/volunteer")
    public ResponseEntity<Volunteer> volunteerLogin() {
        return new ResponseEntity<Volunteer>(getLoggedInUser().getVolunteer(), HttpStatus.OK);
    }

    /**
     * Mapping to log in an organization account
     * @return a HttpStatus corresponding to a successfully logged in organization
     */
    @PostMapping("/organization")
    public ResponseEntity<Organization> organizationLogin() {
        return new ResponseEntity<Organization>(getLoggedInUser().getOrganization(), HttpStatus.OK);
    }

    public User getLoggedInUser() {
        String loggedInUserUsername =
                ((CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUsername();

        User loggedInUser = authService.findUserByUsername(loggedInUserUsername);

        return loggedInUser;
    }
}
