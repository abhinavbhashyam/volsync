package com.volsync.volsyncproject.controller;

import com.volsync.volsyncproject.service.VolunteerPostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
    public HttpStatus assignPostToVolunteer(@PathVariable Long volunteerId, @PathVariable Long postId) {
        volunteerPostService.assignPostToVolunteer(volunteerId, postId);

        return HttpStatus.OK;
    }

    @PutMapping("/{volunteerId}/posts/{postId}/status/{newStatus}")
    public HttpStatus updateStatusForVolunteerPost(@PathVariable Long volunteerId, @PathVariable Long postId,
                                                   @PathVariable String newStatus) {
        volunteerPostService.updateStatusForVolunteerPost(volunteerId, postId, newStatus);

        return HttpStatus.OK;
    }
}
