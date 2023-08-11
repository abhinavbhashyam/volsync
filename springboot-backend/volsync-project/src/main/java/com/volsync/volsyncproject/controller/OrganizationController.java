package com.volsync.volsyncproject.controller;

import com.volsync.volsyncproject.model.Organization;
import com.volsync.volsyncproject.service.OrganizationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


/**
 * Handles HTTP requests involving the organization table in our database
 */
@RestController
@RequestMapping("api/v1/organizations")
public class OrganizationController {

    // reference to service layer
    private final OrganizationService organizationService;

    /**
     * Dependency injection for organizationService
     * @param organizationService service layer to interact with organization table in database
     */
    @Autowired
    public OrganizationController(OrganizationService organizationService) {
        this.organizationService = organizationService;
    }

    /**
     * Creates an organization within the database
     * @param organization the organization to create
     * @return a HttpStatus indicating the status of this request
     */
    @PostMapping
    public HttpStatus createOrganization(@RequestBody Organization organization) {
        organizationService.createOrganization(organization);

        return HttpStatus.NO_CONTENT;
    }

    /**
     * Assigns to an organization its corresponding user account (user accounts are used to authenticate organization)
     * @param organizationId the id of the organization we are assigning the user account to
     * @param userId the id of the user entity in the users table that corresponds to organization with id = organizationId
     * @return a HttpStatus indicating the status of this request
     */
    @PutMapping("/{organizationId}/users/{userId}")
    public HttpStatus assignUserToOrganization(@PathVariable Long organizationId, @PathVariable Long userId) {
        organizationService.assignUserToOrganization(organizationId, userId);

        return HttpStatus.NO_CONTENT;

    }

    /**
     * Gets an organization from the database by id
     * @param organizationId the id of the organization we want to get
     * @return a ResponseEntity corresponding to the organization we are trying to get
     */
    @GetMapping("/{organizationId}")
    public ResponseEntity<Organization> getOrganizationById(@PathVariable Long organizationId) {
        Organization organization = organizationService.getOrganizationById(organizationId);

        return new ResponseEntity<Organization>(organization, HttpStatus.OK);

    }

}
