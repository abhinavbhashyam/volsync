package com.volsync.volsyncproject.repository;

import com.volsync.volsyncproject.model.Volunteer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository layer that handles database operations involving volunteer table
 */
@Repository
public interface VolunteerRepository extends JpaRepository<Volunteer, Long> {
}
