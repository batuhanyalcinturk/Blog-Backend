package com.graysan.blog.controller;

import com.graysan.blog.entities.Like;
import com.graysan.blog.request.LikeCreateRequest;
import com.graysan.blog.service.LikeService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/likes")
public class LikeController {
    LikeService likeService;

    public LikeController(LikeService likeService) {
        this.likeService = likeService;
    }

    @GetMapping
    public List<Like> getAllLikes(@RequestParam Optional<Long> userId, @RequestParam Optional<Long> postId){
        return likeService.getAllLikesWithParam(userId,postId);
    }

    @PostMapping
    public Like createOneLike(@RequestBody LikeCreateRequest requestLike) {
        return likeService.createOneLike(requestLike);
    }

    @GetMapping("/{likeId}")
    public Like getOneLike(@PathVariable Long likeId) {
        return likeService.getOneLikeById(likeId);
    }

    @DeleteMapping("/{likeId}")
    public void deleteOneLike(@PathVariable Long likeId) {
        likeService.deleteOneLikeById(likeId);
    }

}
