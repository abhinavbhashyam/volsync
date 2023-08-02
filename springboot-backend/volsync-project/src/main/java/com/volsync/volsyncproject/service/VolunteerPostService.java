package com.volsync.volsyncproject.service;

import com.volsync.volsyncproject.exception.ResourceNotFoundException;
import com.volsync.volsyncproject.model.Post;
import com.volsync.volsyncproject.model.Status;
import com.volsync.volsyncproject.model.Volunteer;
import com.volsync.volsyncproject.model.VolunteerPost;
import com.volsync.volsyncproject.pk.VolunteerPostId;
import com.volsync.volsyncproject.repository.PostRepository;
import com.volsync.volsyncproject.repository.VolunteerPostRepository;
import com.volsync.volsyncproject.repository.VolunteerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VolunteerPostService {

    private final VolunteerPostRepository volunteerPostRepository;

    private final VolunteerRepository volunteerRepository;

    private final PostRepository postRepository;

    @Autowired
    public VolunteerPostService(VolunteerPostRepository volunteerPostRepository,
                                VolunteerRepository volunteerRepository, PostRepository postRepository) {
        this.volunteerPostRepository = volunteerPostRepository;
        this.volunteerRepository = volunteerRepository;
        this.postRepository = postRepository;
    }

    public VolunteerPost assignPostToVolunteer(Long volunteerId, Long postId) {
        Volunteer volunteer = volunteerRepository.findById(volunteerId)
                .orElseThrow(() -> new ResourceNotFoundException("Volunteer doesn't exist with id: " + volunteerId));
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new ResourceNotFoundException("Post doesn't exist with id: " + postId));

        VolunteerPost entrySignUp = new VolunteerPost();


        entrySignUp.setPost(post);
        entrySignUp.setVolunteer(volunteer);
        entrySignUp.setStatus(Status.PENDING);

        return volunteerPostRepository.save(entrySignUp);

    }


    public VolunteerPost updateStatusForVolunteerPost(Long volunteerId, Long postId, String newStatus) {
        VolunteerPost toUpdate = volunteerPostRepository.findById(new VolunteerPostId(volunteerId, postId))
                .orElseThrow(() -> new ResourceNotFoundException("Entry not found in join table with volunteerId: "
                        + volunteerId + " and postId: " + postId));

        toUpdate.setStatus(Status.valueOf(newStatus));


        return volunteerPostRepository.save(toUpdate);

    }
}
