package com.volsync.volsyncproject.service;

import com.volsync.volsyncproject.exception.ResourceNotFoundException;
import com.volsync.volsyncproject.model.*;
import com.volsync.volsyncproject.repository.PostRepository;
import com.volsync.volsyncproject.repository.UserRepository;
import com.volsync.volsyncproject.repository.VolunteerPostRepository;
import com.volsync.volsyncproject.repository.VolunteerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Service layer that directly interacts with repository layer to perform database operations
 */
@Service
public class VolunteerService {

    // reference to volunteer repository
    public final VolunteerRepository volunteerRepository;

    // reference to user repository
    public final UserRepository userRepository;


    /**
     * Dependency injection for volunteerRepository and userRepository
     * @param volunteerRepository reference to volunteer repository layer
     * @param userRepository reference to user repository layer
     */
    @Autowired
    public VolunteerService(VolunteerRepository volunteerRepository, UserRepository userRepository,
                            VolunteerPostRepository volunteerPostRepository, PostRepository postRepository) {
        this.volunteerRepository = volunteerRepository;
        this.userRepository = userRepository;
    }

    /**
     * Calls on volunteer repository to save the given volunteer
     * @param volunteer the volunteer to save to the repository
     */
    public void createVolunteer(Volunteer volunteer) {
        volunteerRepository.save(volunteer);
    }

    /**
     * Assigns to the given volunteer the user account that corresponds to it
     * @param volunteerId the id of the given volunteer
     * @param userId the id of the corresponding user account
     */
    public void assignUserToVolunteer(Long volunteerId, Long userId) {
        // find volunteer and user, throwing exceptions if they don't exist in database
        Volunteer volunteer = volunteerRepository.findById(volunteerId)
                .orElseThrow(() -> new ResourceNotFoundException("Volunteer doesn't exist with id: " + volunteerId));
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User doesn't exist with id: " + userId));

        // assign foreign key reference
        volunteer.setUser(user);


        // save the updated volunteer
        volunteerRepository.save(volunteer);
    }

    /**
     * Gets a volunteer by its id from the database
     * @param volunteerId the id of the volunteer we want to get
     * @return the volunteer with id = volunteerId
     */
    public Volunteer getVolunteerById(Long volunteerId) {
        // find the organization
        Volunteer volunteer = volunteerRepository.findById(volunteerId)
                .orElseThrow(() -> new ResourceNotFoundException("Volunteer doesn't exist with id: " + volunteerId));

        // return it
        return volunteer;
    }
}
