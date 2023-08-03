package com.volsync.volsyncproject.repository;

import com.volsync.volsyncproject.model.Organization;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository for the organization table in our database
 */
@Repository
public interface OrganizationRepository extends JpaRepository<Organization, Long> {
}
