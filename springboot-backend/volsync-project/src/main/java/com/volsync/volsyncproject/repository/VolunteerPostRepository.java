package com.volsync.volsyncproject.repository;

import com.volsync.volsyncproject.model.VolunteerPost;
import com.volsync.volsyncproject.primary_key.VolunteerPostId;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repository layer to interact with our join table
 *
 * Note: primary key type is the custom primary key we defined
 */
public interface VolunteerPostRepository extends JpaRepository<VolunteerPost, VolunteerPostId> {
}
