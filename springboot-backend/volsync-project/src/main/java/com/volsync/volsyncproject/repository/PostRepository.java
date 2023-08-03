package com.volsync.volsyncproject.repository;

import com.volsync.volsyncproject.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository for the post table in our database
 */
@Repository
public interface PostRepository extends JpaRepository<Post, Long> {
}
