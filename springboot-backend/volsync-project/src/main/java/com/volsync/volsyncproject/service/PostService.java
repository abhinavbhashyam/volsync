package com.volsync.volsyncproject.service;

import com.volsync.volsyncproject.exception.ResourceNotFoundException;
import com.volsync.volsyncproject.model.Organization;
import com.volsync.volsyncproject.model.Post;
import com.volsync.volsyncproject.repository.OrganizationRepository;
import com.volsync.volsyncproject.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Service layer to interact with repository layer of Post and Organization
 */
@Service
public class PostService {

    // reference to post repository
    private final PostRepository postRepository;

    // reference to organization repository
    private final OrganizationRepository organizationRepository;

    /**
     * Dependency injection for postRepository and organizationRepository
     * @param postRepository reference to post repository
     * @param organizationRepository reference to organization repository
     */
    @Autowired
    public PostService(PostRepository postRepository, OrganizationRepository organizationRepository) {
        this.postRepository = postRepository;
        this.organizationRepository = organizationRepository;
    }

    /**
     * Creates a post within the database
     *
     * @param post the post to create
     * @return the created post
     */
    public Post createPost(Post post) {
        return postRepository.save(post);
    }

    /**
     * Assigns to a post the organization that posted it
     * @param postId the id of the post we are assigning the organization to
     * @param organizationId the id of the organization that posted the post with id = postId
     */
    public void assignOrganizationToPost(Long postId, Long organizationId) {
        // find the post and organization, throwing exceptions if ids are invalid
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new ResourceNotFoundException("Post doesn't exist with id: " + postId));
        Organization organization = organizationRepository.findById(organizationId)
                .orElseThrow(() -> new ResourceNotFoundException("Organization doesn't exist with id: " + organizationId));

        // initialize the foreign key reference
        post.setPostedByOrganization(organization);

        // save the post
        postRepository.save(post);
    }

}
