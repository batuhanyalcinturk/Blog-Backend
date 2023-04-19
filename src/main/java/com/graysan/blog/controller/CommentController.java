package com.graysan.blog.controller;

import com.graysan.blog.entities.Comment;
import com.graysan.blog.request.CommentCreateRequest;
import com.graysan.blog.request.CommentUpdateRequest;
import com.graysan.blog.service.CommentService;

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
@RequestMapping({"/comments"})
public class CommentController {
    CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @GetMapping
    public List<Comment> getAllComments(@RequestParam Optional<Long> userId, @RequestParam Optional<Long> postId) {
        return this.commentService.getAllCommentsWithParam(userId, postId);
    }

    @PostMapping
    public Comment createOneComment(@RequestBody CommentCreateRequest requestComment) {
        return this.commentService.createOneComment(requestComment);
    }

    @GetMapping({"/{commentId}"})
    public Comment getOneComment(@PathVariable Long commentId) {
        return this.commentService.getOneCommentById(commentId);
    }

    @PutMapping({"/{commentId}"})
    public Comment updateOneComment(@PathVariable Long commentId, @RequestBody CommentUpdateRequest commentUpdate) {
        return this.commentService.updateOneCommentById(commentId, commentUpdate);
    }

    @DeleteMapping({"/{commentId}"})
    public void deleteOneComment(@PathVariable Long commentId) {
        this.commentService.deleteOneCommentById(commentId);
    }
}
