package com.volsync.volsyncproject.controller;

import com.volsync.volsyncproject.model.Organization;
import com.volsync.volsyncproject.service.OrganizationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/organizations")
public class OrganizationController {

    private final OrganizationService organizationService;

    @Autowired
    public OrganizationController(OrganizationService organizationService) {
        this.organizationService = organizationService;
    }

    @PostMapping
    public ResponseEntity<Organization> createOrganization(@RequestBody Organization organization) {
        Organization createdOrganization = organizationService.createOrganization(organization);

        return new ResponseEntity<Organization>(createdOrganization, HttpStatus.CREATED);
    }

    @PutMapping("/{organizationId}/users/{userId}")
    public ResponseEntity<Organization> assignUserToOrganization(@PathVariable Long organizationId, @PathVariable Long userId) {
        Organization assignedOrganization = organizationService.assignUserToVolunteer(organizationId, userId);

        return new ResponseEntity<Organization>(assignedOrganization, HttpStatus.OK);
    }
}
