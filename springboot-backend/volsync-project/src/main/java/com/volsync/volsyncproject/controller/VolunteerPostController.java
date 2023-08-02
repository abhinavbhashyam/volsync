package com.volsync.volsyncproject.controller;

import com.volsync.volsyncproject.model.VolunteerPost;
import com.volsync.volsyncproject.service.VolunteerPostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/volunteer-posts/volunteers")
public class VolunteerPostController {

    private final VolunteerPostService volunteerPostService;

    @Autowired
    public VolunteerPostController(VolunteerPostService volunteerPostService) {
        this.volunteerPostService = volunteerPostService;
    }

    @PostMapping("/{volunteerId}/posts/{postId}")
    public ResponseEntity<VolunteerPost> assignPostToVolunteer(@PathVariable Long volunteerId, @PathVariable Long postId) {
        VolunteerPost volunteerPost = volunteerPostService.assignPostToVolunteer(volunteerId, postId);

        return new ResponseEntity<VolunteerPost>(volunteerPost, HttpStatus.CREATED);
    }

    @PutMapping("/{volunteerId}/posts/{postId}")
    public ResponseEntity<VolunteerPost>
    updateStatusForVolunteerPost(@PathVariable Long volunteerId, @PathVariable Long postId,
                                                                      @RequestParam("status") String newStatus) {
        VolunteerPost updatedVolunteerPost =
                volunteerPostService.updateStatusForVolunteerPost(volunteerId, postId, newStatus);

        return new ResponseEntity<VolunteerPost>(updatedVolunteerPost, HttpStatus.OK);
    }
}
