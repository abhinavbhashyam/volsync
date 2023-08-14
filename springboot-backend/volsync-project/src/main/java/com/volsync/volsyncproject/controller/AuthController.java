package com.volsync.volsyncproject.controller;

import com.volsync.volsyncproject.auth_user_details.CustomUserDetails;
import com.volsync.volsyncproject.model.Organization;
import com.volsync.volsyncproject.model.User;
import com.volsync.volsyncproject.model.Volunteer;
import com.volsync.volsyncproject.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
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

    // reference to auth service class
    public final AuthService authService;

    /**
     * Dependency injection for auth service
     * @param authService reference to auth service
     */
    @Autowired
    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    /**
     * Mapping to log in a volunteer account
     * @return a ResponseEntity corresponding to a successfully logged in volunteer
     */
    @PostMapping("/volunteer")
    public ResponseEntity<Volunteer> volunteerLogin() {
        // note: CREATED status b/c we are creating a volunteer "session" within app
        return new ResponseEntity<Volunteer>(authService.getLoggedInUser().getVolunteer(), HttpStatus.CREATED);
    }

    /**
     * Mapping to log in an organization account
     * @return a ResponseEntity corresponding to a successfully logged in organization
     */
    @PostMapping("/organization")
    public ResponseEntity<Organization> organizationLogin() {
        // note: CREATED status b/c we are creating an organization "session" within app
        return new ResponseEntity<Organization>(authService.getLoggedInUser().getOrganization(), HttpStatus.CREATED);
    }

//    /**
//     * Private helper method to help us fetch the user information of the currently logged in user
//     * @return the object corresponding to the currently logged in user
//     */
//    private User getLoggedInUser() {
//        // get the user that's currently logged in
//
//        String loggedInUser =
//                ((CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUser();
//
//        // return user object
//        return loggedInUser;
//    }
}
