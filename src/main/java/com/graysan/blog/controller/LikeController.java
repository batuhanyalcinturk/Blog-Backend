package com.graysan.blog.controller;

import com.graysan.blog.entities.Like;
import com.graysan.blog.request.LikeCreateRequest;
import com.graysan.blog.response.LikeResponse;
import com.graysan.blog.service.LikeService;
import java.util.List;
import java.util.Optional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping({"/likes"})
public class LikeController {
    LikeService likeService;

    public LikeController(LikeService likeService) {
        this.likeService = likeService;
    }

    @GetMapping
    public List<LikeResponse> getAllLikes(@RequestParam Optional<Long> userId, @RequestParam Optional<Long> postId) {
        return this.likeService.getAllLikesWithParam(userId, postId);
    }

    @PostMapping
    public Like createOneLike(@RequestBody LikeCreateRequest requestLike) {
        return this.likeService.createOneLike(requestLike);
    }

    @GetMapping({"/{likeId}"})
    public Like getOneLike(@PathVariable Long likeId) {
        return this.likeService.getOneLikeById(likeId);
    }

    @DeleteMapping({"/{likeId}"})
    public void deleteOneLike(@PathVariable Long likeId) {
        this.likeService.deleteOneLikeById(likeId);
    }
}
