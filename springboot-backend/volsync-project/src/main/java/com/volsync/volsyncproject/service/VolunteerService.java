package com.volsync.volsyncproject.service;

import com.volsync.volsyncproject.exception.ResourceNotFoundException;
import com.volsync.volsyncproject.model.User;
import com.volsync.volsyncproject.model.Volunteer;
import com.volsync.volsyncproject.repository.UserRepository;
import com.volsync.volsyncproject.repository.VolunteerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VolunteerService {

    public final VolunteerRepository volunteerRepository;
    public final UserRepository userRepository;

    @Autowired
    public VolunteerService(VolunteerRepository volunteerRepository, UserRepository userRepository) {
        this.volunteerRepository = volunteerRepository;
        this.userRepository = userRepository;
    }

    public Volunteer createVolunteer(Volunteer volunteer) {
        return volunteerRepository.save(volunteer);
    }

    public Volunteer assignUserToVolunteer(Long volunteerId, Long userId) {
        Volunteer volunteer = volunteerRepository.findById(volunteerId)
                .orElseThrow(() -> new ResourceNotFoundException("Volunteer doesn't exist with id: " + volunteerId));
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User doesn't exist with id: " + userId));

        volunteer.setUser(user);

        return volunteerRepository.save(volunteer);
    }

}
