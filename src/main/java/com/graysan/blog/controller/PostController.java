package com.graysan.blog.controller;

import com.graysan.blog.entities.Post;
import com.graysan.blog.request.PostCreateRequest;
import com.graysan.blog.request.PostUpdateRequest;
import com.graysan.blog.service.PostService;
import java.util.List;
import java.util.Optional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping({"/posts"})
public class PostController {
    PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    @GetMapping
    public List<Post> getAllPosts(@RequestParam Optional<Long> userId) {
        return this.postService.getAllPosts(userId);
    }

    @PostMapping
    public Post createOnePost(@RequestBody PostCreateRequest newPostRequest) {
        return this.postService.createOnePost(newPostRequest);
    }

    @GetMapping({"/{postId}"})
    public Post getOnePost(@PathVariable Long postId) {
        return this.postService.getOnePostById(postId);
    }

    @PutMapping({"/{postId}"})
    public Post updateOnePost(@PathVariable Long postId, @RequestBody PostUpdateRequest updatePost) {
        return this.postService.updateOnePostById(postId, updatePost);
    }

    @DeleteMapping({"/{postId}"})
    public void deleteOnePost(@PathVariable Long postId) {
        this.postService.deleteOnePostById(postId);
    }
}
