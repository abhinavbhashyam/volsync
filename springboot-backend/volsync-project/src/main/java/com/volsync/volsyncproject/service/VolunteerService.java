package com.volsync.volsyncproject.service;

import com.volsync.volsyncproject.exception.ResourceNotFoundException;
import com.volsync.volsyncproject.model.*;
import com.volsync.volsyncproject.repository.PostRepository;
import com.volsync.volsyncproject.repository.UserRepository;
import com.volsync.volsyncproject.repository.VolunteerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Stream;

/**
 * Service layer that directly interacts with repository layer to perform database operations
 */
@Service
public class VolunteerService {

    // reference to volunteer repository
    public final VolunteerRepository volunteerRepository;

    // reference to user repository
    public final UserRepository userRepository;

    // reference to post repository
    public final PostRepository postRepository;


    /**
     * Dependency injection for volunteerRepository, userRepository, postRepository
     * @param volunteerRepository reference to volunteer repository layer
     * @param userRepository reference to user repository layer
     * @param postRepository reference to our post repository layer
     */
    @Autowired
    public VolunteerService(VolunteerRepository volunteerRepository, UserRepository userRepository, PostRepository postRepository) {
        this.volunteerRepository = volunteerRepository;
        this.userRepository = userRepository;
        this.postRepository = postRepository;
    }

    /**
     * Calls on volunteer repository to save the given volunteer
     *
     * @param volunteer the volunteer to save to the repository
     * @return the newly created volunteer
     */
    public Volunteer createVolunteer(Volunteer volunteer) {
        return volunteerRepository.save(volunteer);
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
        // find the volunteer
        Volunteer volunteer = volunteerRepository.findById(volunteerId)
                .orElseThrow(() -> new ResourceNotFoundException("Volunteer doesn't exist with id: " + volunteerId));

        // return it
        return volunteer;
    }

    /**
     * Gets all the posts a volunteer can discover
     * @param volunteerId the id of the volunteer
     * @return the set of posts a volunteer can discover
     */
    public Set<Post> getDiscoverPostsForVolunteer(Long volunteerId) {
        // find the volunteer
        Volunteer volunteer = volunteerRepository.findById(volunteerId)
                .orElseThrow(() -> new ResourceNotFoundException("Volunteer doesn't exist with id: " + volunteerId));

        // list of all posts
        Set<Post> allPosts = new HashSet<>(postRepository.findAll());

        // references to our signed up/accepted/rejected lists
        Set<Post> signedUpPosts = volunteer.getSignedUpPosts();
        Set<Post> acceptedToPosts = volunteer.getAcceptedToPosts();
        Set<Post> rejectedFromPosts = volunteer.getRejectedFromPosts();

        // need to combine all posts that I've signed up/accepted to/rejected from
        Set<Post> sarPosts = new HashSet<>();

        Stream.of(signedUpPosts, acceptedToPosts, rejectedFromPosts).forEach(sarPosts::addAll);

        // now I want to remove from all posts the ones I've already interacted with
        allPosts.removeAll(sarPosts);

        // return the removed result
        return allPosts;
    }
}
