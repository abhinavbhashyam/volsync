package com.volsync.volsyncproject.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

/**
 * Controller to handle login requests (for volunteer and organization roles)
 */
@CrossOrigin("*")
@RestController
@RequestMapping("/api/v1/login")
public class AuthController {

    /**
     * Mapping to log in a volunteer account
     *
     * @return a HttpStatus corresponding to successful login of a volunteer
     */
    @PostMapping("/volunteer")
    public HttpStatus volunteerLogin() {
        // note: post mapping b/c we are creating a volunteer "session" within app
        return HttpStatus.NO_CONTENT;
    }

    /**
     * Mapping to log in an organization account
     *
     * @return a HttpStatus corresponding to successful login of an organization
     */
    @PostMapping("/organization")
    public HttpStatus organizationLogin() {
        // note: post mapping b/c we are creating an organization "session" within app
        return HttpStatus.NO_CONTENT;
    }
}
