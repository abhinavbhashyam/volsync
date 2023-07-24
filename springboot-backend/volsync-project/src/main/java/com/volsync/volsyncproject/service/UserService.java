package com.volsync.volsyncproject.service;

import com.volsync.volsyncproject.exception.ResourceNotFoundException;
import com.volsync.volsyncproject.model.Organization;
import com.volsync.volsyncproject.model.User;
import com.volsync.volsyncproject.model.Volunteer;
import com.volsync.volsyncproject.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Handles all interaction with the UserRepository
 */
@Service
public class UserService {

    // reference to the user repository later
    private final UserRepository userRepository;

    /**
     * Dependency injection for userRepository
     * @param userRepository instance of our repository class
     */
    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    /**
     * Create the given user by saving it to the database
     * @param user the user to create
     * @return the user that was added to the database
     */
    public User createUser(User user) {
        return userRepository.save(user);

    }

    /**
     * Finds the user with id = userId and then sets that user's corresponding volunteer to be the given volunteer.
     * Since we have CascadeType.PERSIST in the User class, the child Volunteer entity will also be created in the database
     * @param volunteer the volunteer we are assigning to the user
     * @param userId the id of the user the volunteer is being assiged to
     * @return the user object after the volunteer has been assigned to it
     */
    public User createVolunteerAndSyncUserAccount(Volunteer volunteer, Long userId) {
        // find the user we want to assign the volunteer to
        User userToAssign = userRepository.findById(userId).orElseThrow(() ->
            new ResourceNotFoundException("User doesn't exist with id: " + userId));

        // assign the volunteer to that user
        userToAssign.setVolunteer(volunteer);

        // assign foreign key reference to the user we just assigned the volunteer to
        volunteer.setUser(userToAssign);

        // save the configured user (cascade type in User class results in Volunteer also being saved)
        userRepository.save(userToAssign);

        // return the configured user
        return userToAssign;


    }


    /**
     * Finds the user with id = userId and then sets that user's corresponding organization to be the given organization.
     * Since we have CascadeType.PERSIST in the User class, the child Organization entity will also be created in the database
     * @param organization the organization we are assigning to the user
     * @param userId the id of the user the organization is being assigned to
     * @return the user object after the organization has been assigned to it
     */
    public User createOrganizationAndSyncUserAccount(Organization organization, Long userId) {
        // find the user we want to assign the organization to
        User userToAssign = userRepository.findById(userId).orElseThrow(() ->
                new ResourceNotFoundException("User doesn't exist with id: " + userId));

        // assign the organization to that user
        userToAssign.setOrganization(organization);

        // assign foreign key reference to the user we just assigned the organization to
        organization.setUser(userToAssign);

        // save the configured user (cascade type in User class results in Organization also being saved)
        userRepository.save(userToAssign);

        // return the configured user
        return userToAssign;
    }
}
