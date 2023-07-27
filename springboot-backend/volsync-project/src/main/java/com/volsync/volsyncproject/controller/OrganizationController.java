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
     * @return a ResponseEntity corresponding to the successfully created organization
     */
    @PostMapping
    public ResponseEntity<Organization> createOrganization(@RequestBody Organization organization) {
        Organization createdOrganization = organizationService.createOrganization(organization);

        return new ResponseEntity<Organization>(createdOrganization, HttpStatus.CREATED);
    }

    /**
     * Assigns to an organization its corresponding user account (user accounts are used to authenticate organization)
     * @param organizationId the id of the organization we are assigning the user account to
     * @param userId the id of the user entry in the users table that corresponds to organization with id = organizationId
     * @return a ResponseEntity corresponding to the organization which has just been assigned a user account
     */
    @PutMapping("/{organizationId}/users/{userId}")
    public ResponseEntity<Organization> assignUserToOrganization(@PathVariable Long organizationId, @PathVariable Long userId) {
        Organization assignedOrganization = organizationService.assignUserToOrganization(organizationId, userId);

        return new ResponseEntity<Organization>(assignedOrganization, HttpStatus.OK);
    }
}
