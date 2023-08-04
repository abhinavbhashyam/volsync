package com.volsync.volsyncproject.controller;

import com.volsync.volsyncproject.auth_user_details.CustomUserDetails;
import com.volsync.volsyncproject.model.Organization;
import com.volsync.volsyncproject.model.User;
import com.volsync.volsyncproject.model.Volunteer;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

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
    @PostMapping("/volunteer")
    public ResponseEntity<Volunteer> volunteerLogin() {
        return new ResponseEntity<Volunteer>(getLoggedInUser().getVolunteer(), HttpStatus.OK);
    }

    /**
     * Mapping to log in an organization account
     * @return a ResponseEntity corresponding to a successfully logged in organization
     */
    @PostMapping("/organization")
    public ResponseEntity<Organization> organizationLogin() {
        return new ResponseEntity<Organization>(getLoggedInUser().getOrganization(), HttpStatus.OK);
    }

    /**
     * Private helper method to help us fetch the user information of the currently logged in user
     * @return the object corresponding to the currently logged in user
     */
    public User getLoggedInUser() {
        // get the user that's currently logged in
        User loggedInUser =
                ((CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUser();

        // return user object
        return loggedInUser;
    }
}
