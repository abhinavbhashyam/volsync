package com.volsync.volsyncproject.repository;

import com.volsync.volsyncproject.model.Organization;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
public interface OrganizationRepository extends JpaRepository<Organization, Long> {
}
