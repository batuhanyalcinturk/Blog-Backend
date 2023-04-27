package com.graysan.blog.service;

import com.graysan.blog.entities.Post;
import com.graysan.blog.entities.User;
import com.graysan.blog.repos.PostRepository;
import com.graysan.blog.request.PostCreateRequest;
import com.graysan.blog.request.PostUpdateRequest;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.graysan.blog.response.PostResponse;
import org.springframework.stereotype.Service;

@Service
public class PostService {
    private final PostRepository postRepository;
    private final UserService userService;

    public PostService(PostRepository postRepository, UserService userService) {
        this.postRepository = postRepository;
        this.userService = userService;
    }

    public List<PostResponse> getAllPosts(Optional<Long> userId) {
        List<Post> list;
        if(userId.isPresent()){
            list = postRepository.findByUserId(userId.get());
        }
        list = postRepository.findAll();
        return list.stream().map(PostResponse::new).collect(Collectors.toList());
    }

    public Post getOnePostById(Long postId) {
        return this.postRepository.findById(postId).orElse(null);
    }

    public Post createOnePost(PostCreateRequest newPostRequest) {
        User user = this.userService.getOneUserById(newPostRequest.getUserId());
        if (user == null) {
            return null;
        } else {
            Post toSave = new Post();
            toSave.setId(newPostRequest.getId());
            toSave.setText(newPostRequest.getText());
            toSave.setSummary(newPostRequest.getSummary());
            toSave.setTitle(newPostRequest.getTitle());
            toSave.setUser(user);
            return this.postRepository.save(toSave);
        }
    }

    public Post updateOnePostById(Long postId, PostUpdateRequest updatePost) {
        Optional<Post> post = this.postRepository.findById(postId);
        if (post.isPresent()) {
            Post toUpdate = post.get();
            toUpdate.setText(updatePost.getText());
            toUpdate.setTitle(updatePost.getTitle());
            this.postRepository.save(toUpdate);
            return toUpdate;
        } else {
            return null;
        }
    }

    public void deleteOnePostById(Long postId) {
        this.postRepository.deleteById(postId);
    }
}
