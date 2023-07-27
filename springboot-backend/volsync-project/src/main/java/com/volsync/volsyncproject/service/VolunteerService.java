package com.volsync.volsyncproject.service;

import com.volsync.volsyncproject.exception.ResourceNotFoundException;
import com.volsync.volsyncproject.model.User;
import com.volsync.volsyncproject.model.Volunteer;
import com.volsync.volsyncproject.repository.UserRepository;
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
    public VolunteerService(VolunteerRepository volunteerRepository, UserRepository userRepository) {
        this.volunteerRepository = volunteerRepository;
        this.userRepository = userRepository;
    }

    /**
     * Calls on volunteer repository to save the given volunteer
     * @param volunteer the volunteer to save to the repository
     * @return the saved volunteer
     */
    public Volunteer createVolunteer(Volunteer volunteer) {
        return volunteerRepository.save(volunteer);
    }

    /**
     * Assigns to the given volunteer the user account that corresponds to it
     * @param volunteerId the id of the given volunteer
     * @param userId the id of the corresponding user account
     * @return the newly updated volunteer
     */
    public Volunteer assignUserToVolunteer(Long volunteerId, Long userId) {
        Volunteer volunteer = volunteerRepository.findById(volunteerId)
                .orElseThrow(() -> new ResourceNotFoundException("Volunteer doesn't exist with id: " + volunteerId));
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User doesn't exist with id: " + userId));

        volunteer.setUser(user);

        return volunteerRepository.save(volunteer);
    }

}
