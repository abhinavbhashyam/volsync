package com.volsync.volsyncproject.controller;

import com.volsync.volsyncproject.model.Post;
import com.volsync.volsyncproject.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpSessionRequiredException;
import org.springframework.web.bind.annotation.*;



/**
 * Handles HTTP requests involving the posts table in our database
 */
@CrossOrigin("*")
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

    /**
     * Adds a post to the database (calls on service class)
     * @param post the post to add
     * @return a HttpStatus corresponding to the status of this request
     */
    @PostMapping
    public HttpStatus createPost(@RequestBody Post post) {
        postService.createPost(post);

        return HttpStatus.NO_CONTENT;
    }

    /**
     * Assigns to a post the organization that posted it
     * @param postId the id of the post we are assigning the organization to
     * @param organizationId the id of the organization that is being assigned to the post
     * @return a HttpStatus indicating the status of this request
     */
    @PutMapping("/{postId}/organizations/{organizationId}")
    public HttpStatus assignOrganizationToPost(@PathVariable Long postId, @PathVariable Long organizationId) {
        postService.assignOrganizationToPost(postId, organizationId);

        return HttpStatus.NO_CONTENT;
    }

}
