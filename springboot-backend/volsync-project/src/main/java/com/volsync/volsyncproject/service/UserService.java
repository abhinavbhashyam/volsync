package com.volsync.volsyncproject.service;

import com.volsync.volsyncproject.model.Organization;
import com.volsync.volsyncproject.model.User;
import com.volsync.volsyncproject.model.Volunteer;
import com.volsync.volsyncproject.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User createUser(User user) {
        return userRepository.save(user);

    }

    public void createVolunteerAndAssignUser(Volunteer volunteer, Long id) {
        User userToAssign = userRepository.findById(id).orElse(null);

        userToAssign.setVolunteer(volunteer);

        volunteer.setUser(userToAssign);

        userRepository.save(userToAssign);


    }

    public void createOrganizationAndAssignUser(Organization organization, Long id) {

        User userToAssign = userRepository.findById(id).get();

        userToAssign.setOrganization(organization);

        organization.setUser(userToAssign);

        userRepository.save(userToAssign);
    }
}
