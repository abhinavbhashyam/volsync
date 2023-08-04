package com.volsync.volsyncproject.service;

import com.volsync.volsyncproject.exception.ResourceNotFoundException;
import com.volsync.volsyncproject.model.Post;
import com.volsync.volsyncproject.model.Status;
import com.volsync.volsyncproject.model.Volunteer;
import com.volsync.volsyncproject.model.VolunteerPost;
import com.volsync.volsyncproject.primary_key.VolunteerPostId;
import com.volsync.volsyncproject.repository.PostRepository;
import com.volsync.volsyncproject.repository.VolunteerPostRepository;
import com.volsync.volsyncproject.repository.VolunteerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Service layer to interact with join table
 */
@Service
public class VolunteerPostService {

    // reference to join table repo
    private final VolunteerPostRepository volunteerPostRepository;

    // reference to volunteer repo
    private final VolunteerRepository volunteerRepository;

    // reference to post repo
    private final PostRepository postRepository;

    /**
     * Dependency injection for volunteerPostRepository, volunteerRepository, and postRepository
     * @param volunteerPostRepository reference to join table repo
     * @param volunteerRepository reference to volunteer table repo
     * @param postRepository reference to post table repo
     */
    @Autowired
    public VolunteerPostService(VolunteerPostRepository volunteerPostRepository,
                                VolunteerRepository volunteerRepository, PostRepository postRepository) {
        this.volunteerPostRepository = volunteerPostRepository;
        this.volunteerRepository = volunteerRepository;
        this.postRepository = postRepository;
    }

    /**
     * Signs up a volunteer to a post
     * @param volunteerId the id of the volunteer who is being signed up
     * @param postId the id of the post to which the volunteer is being signed up
     */
    public void assignPostToVolunteer(Long volunteerId, Long postId) {
        // retrieve the volunteer and post from the database, throwing exceptions where necessary
        Volunteer volunteer = volunteerRepository.findById(volunteerId)
                .orElseThrow(() -> new ResourceNotFoundException("Volunteer doesn't exist with id: " + volunteerId));
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new ResourceNotFoundException("Post doesn't exist with id: " + postId));


        VolunteerPost entrySignUp = new VolunteerPost();

        // set the post, volunteer, and status for this entry in our table
        entrySignUp.setPost(post);
        entrySignUp.setVolunteer(volunteer);
        entrySignUp.setStatus(Status.PENDING);

        // save the entity
        volunteerPostRepository.save(entrySignUp);

    }


    /**
     * Updates the status a volunteer has to a post
     * @param volunteerId the id of the volunteer whose status we're updating
     * @param postId the id of the post for which the volunteer's status is being updated
     * @param newStatus the new status
     */
    public void updateStatusForVolunteerPost(Long volunteerId, Long postId, String newStatus) {
        // retrieve the specific row in our join table that we need to update by utilizing our composite primary key
        // class
        VolunteerPost toUpdate = volunteerPostRepository.findById(new VolunteerPostId(volunteerId, postId))
                .orElseThrow(() -> new ResourceNotFoundException("Entry not found in join table with volunteerId: "
                        + volunteerId + " and postId: " + postId));

        // update the status
        toUpdate.setStatus(Status.valueOf(newStatus));


        // save the entry
        volunteerPostRepository.save(toUpdate);

    }
}
