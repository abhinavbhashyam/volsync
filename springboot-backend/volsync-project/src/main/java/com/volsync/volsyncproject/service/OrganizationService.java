package com.volsync.volsyncproject.service;

import com.volsync.volsyncproject.exception.ResourceNotFoundException;
import com.volsync.volsyncproject.model.Organization;
import com.volsync.volsyncproject.model.Post;
import com.volsync.volsyncproject.model.User;
import com.volsync.volsyncproject.model.Volunteer;
import com.volsync.volsyncproject.repository.OrganizationRepository;
import com.volsync.volsyncproject.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

/**
 * Service layer that directly interacts with repository layer to perform database operations
 */
@Service
public class OrganizationService {

    // reference to organization repository
    public final OrganizationRepository organizationRepository;

    // reference to user repository
    public final UserRepository userRepository;

    /**
     * Dependency injection for organizationRepository and userRepository
     * @param organizationRepository reference to organization repository layer
     * @param userRepository reference to user repository layer
     */
    @Autowired
    public OrganizationService(OrganizationRepository organizationRepository, UserRepository userRepository) {
        this.organizationRepository = organizationRepository;
        this.userRepository = userRepository;
    }

    /**
     * Calls on organization repository to save the given organization
     * @param organization the organization to save to the repository
     * @return the saved organization
     */
    public Organization createOrganization(Organization organization) {
        return organizationRepository.save(organization);
    }

    /**
     * Assigns to the given organization the user account that corresponds to it
     * @param organizationId the id of the given organization
     * @param userId the id of the corresponding user account
     * @return the newly updated organization
     */
    public Organization assignUserToOrganization(Long organizationId, Long userId) {
        Organization organization = organizationRepository.findById(organizationId)
                .orElseThrow(() -> new ResourceNotFoundException("Organization doesn't exist with id: " + organizationId));
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User doesn't exist with id: " + userId));

        organization.setUser(user);

        return organizationRepository.save(organization);
    }

    public Set<Post> getAllPostsFromOrganization(Long organizationId) {
        Organization desiredOrganization = organizationRepository.findById(organizationId)
                .orElseThrow(() -> new ResourceNotFoundException("Organization doesn't exist with id: " + organizationId));

        return desiredOrganization.getPosts();

    }
}
