//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.graysan.blog.service;

import com.graysan.blog.entities.Like;
import com.graysan.blog.entities.Post;
import com.graysan.blog.entities.User;
import com.graysan.blog.repos.LikeRepository;
import com.graysan.blog.request.LikeCreateRequest;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;

@Service
public class LikeService {
    private final LikeRepository likeRepository;
    private final UserService userService;
    private final PostService postService;

    public LikeService(LikeRepository likeRepository, UserService userService, PostService postService) {
        this.likeRepository = likeRepository;
        this.userService = userService;
        this.postService = postService;
    }

    public List<Like> getAllLikesWithParam(Optional<Long> userId, Optional<Long> postId) {
        if (userId.isPresent() && postId.isPresent()) {
            return this.likeRepository.findByUserIdAndPostId(userId.get(), postId.get());
        } else if (userId.isPresent()) {
            return this.likeRepository.findByUserId(userId.get());
        } else {
            return postId.isPresent() ? this.likeRepository.findByPostId(postId.get()) : this.likeRepository.findAll();
        }
    }

    public Like getOneLikeById(Long likeId) {
        return this.likeRepository.findById(likeId).orElse(null);
    }

    public Like createOneLike(LikeCreateRequest requestLike) {
        User user = this.userService.getOneUserById(requestLike.getUserId());
        Post post = this.postService.getOnePostById(requestLike.getPostId());
        if (user != null && post != null) {
            Like likeToSave = new Like();
            likeToSave.setId(requestLike.getId());
            likeToSave.setPost(post);
            likeToSave.setUser(user);
            return this.likeRepository.save(likeToSave);
        } else {
            return null;
        }
    }

    public void deleteOneLikeById(Long likeId) {
        this.likeRepository.deleteById(likeId);
    }
}
