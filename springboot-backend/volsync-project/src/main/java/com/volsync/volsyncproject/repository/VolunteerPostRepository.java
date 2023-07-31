package com.volsync.volsyncproject.repository;

import com.volsync.volsyncproject.model.Volunteer;
import com.volsync.volsyncproject.model.VolunteerPost;
import com.volsync.volsyncproject.pk.VolunteerPostId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VolunteerPostRepository extends JpaRepository<VolunteerPost, VolunteerPostId> {
}
