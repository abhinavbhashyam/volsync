package com.volsync.volsyncproject.controller;

import com.volsync.volsyncproject.service.VolunteerPostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

/**
 * Handles all requests involving the join table (between volunteers and posts (allows us to track which volunteers
 * have signed up for which posts, and what their status to that post is (pending, accepted, rejected)))
 */
@CrossOrigin("*")
@RestController
@RequestMapping("api/v1/volunteer-posts")
public class VolunteerPostController {

    // reference to service layer
    private final VolunteerPostService volunteerPostService;

    /**
     * Dependency injection for volunteerPostService
     * @param volunteerPostService service layer to interact with db
     */
    @Autowired
    public VolunteerPostController(VolunteerPostService volunteerPostService) {
        this.volunteerPostService = volunteerPostService;
    }

    /**
     * Signs up a volunteer to a post (initial status = pending)
     * @param volunteerId the id of the volunteer we are signing up to the post
     * @param postId the id of the post that we are signing up the volunteer to
     * @return a HttpStatus indicating the status of this request
     */
    @PostMapping("/volunteers/{volunteerId}/posts/{postId}")
    public HttpStatus assignPostToVolunteer(@PathVariable Long volunteerId, @PathVariable Long postId) {
        volunteerPostService.assignPostToVolunteer(volunteerId, postId);

        return HttpStatus.NO_CONTENT;
    }

    /**
     * Updates a volunteer's status on a post
     * @param volunteerId the id of the volunteer whose status we are updating
     * @param postId the id of the post for which the volunteer's status is being updated
     * @param newStatus the new status to the post
     * @return a HttpStatus indicating the status of this request
     */
    @PutMapping("/volunteers/{volunteerId}/posts/{postId}")
    public HttpStatus
    updateStatusForVolunteerPost(@PathVariable Long volunteerId, @PathVariable Long postId,
                                                                      @RequestParam("status") String newStatus) {
        volunteerPostService.updateStatusForVolunteerPost(volunteerId, postId, newStatus);

        return HttpStatus.NO_CONTENT;
    }
}
