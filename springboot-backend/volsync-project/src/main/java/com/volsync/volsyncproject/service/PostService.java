package com.volsync.volsyncproject.service;

import com.volsync.volsyncproject.exception.ResourceNotFoundException;
import com.volsync.volsyncproject.model.Organization;
import com.volsync.volsyncproject.model.Post;
import com.volsync.volsyncproject.repository.OrganizationRepository;
import com.volsync.volsyncproject.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Service
public class PostService {

    private final PostRepository postRepository;

    private final OrganizationRepository organizationRepository;

    @Autowired
    public PostService(PostRepository postRepository, OrganizationRepository organizationRepository) {
        this.postRepository = postRepository;
        this.organizationRepository = organizationRepository;
    }

    public void createPost(Post post) {
        postRepository.save(post);
    }

    public void assignOrganizationToPost(Long postId, Long organizationId) {
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new ResourceNotFoundException("Post doesn't exist with id: " + postId));
        Organization organization = organizationRepository.findById(organizationId)
                .orElseThrow(() -> new ResourceNotFoundException("Organization doesn't exist with id: " + organizationId));

        post.setPostedByOrganization(organization);

        postRepository.save(post);
    }
}
