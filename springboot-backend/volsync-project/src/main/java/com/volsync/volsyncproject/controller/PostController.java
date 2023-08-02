package com.volsync.volsyncproject.controller;

import com.volsync.volsyncproject.model.Post;
import com.volsync.volsyncproject.model.Volunteer;
import com.volsync.volsyncproject.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.parameters.P;
import org.springframework.security.web.firewall.HttpStatusRequestRejectedHandler;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;

/**
 * Handles HTTP requests involving the posts table in our database
 */

@RestController
@RequestMapping("api/v1/posts")
public class PostController {

    // reference to service layer
    private final PostService postService;

    /**
     * Dependency injection for postService
     * @param postService service layer to interact with post table in database
     */
    @Autowired
    public PostController(PostService postService) {
        this.postService = postService;
    }

    @PostMapping
    public ResponseEntity<Post> createPost(@RequestBody Post post) {
        Post createdPost = postService.createPost(post);

        return new ResponseEntity<Post>(createdPost, HttpStatus.CREATED);
    }

    @PutMapping("/{postId}/organizations/{organizationId}")
    public HttpStatus assignOrganizationToPost(@PathVariable Long postId, @PathVariable Long organizationId) {
        postService.assignOrganizationToPost(postId, organizationId);

        return HttpStatus.NO_CONTENT;
    }
}
