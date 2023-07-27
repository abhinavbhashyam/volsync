package com.volsync.volsyncproject.repository;

import com.volsync.volsyncproject.model.Organization;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

/**
 * Repository for the organization table in our database
 */
@Repository
public interface OrganizationRepository extends JpaRepository<Organization, Long> {
}
