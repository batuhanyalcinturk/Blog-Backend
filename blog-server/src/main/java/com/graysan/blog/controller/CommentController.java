package com.graysan.blog.controller;

import com.graysan.blog.entities.Comment;
import com.graysan.blog.request.CommentCreateRequest;
import com.graysan.blog.request.CommentUpdateRequest;
import com.graysan.blog.service.CommentService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/comments")
public class CommentController {
    CommentService commentService;

    public CommentController(CommentService commentService){
        this.commentService = commentService;
    }

    @GetMapping
    public List<Comment> getAllComments(@RequestParam Optional<Long> userId, @RequestParam Optional<Long> postId){
        return commentService.getAllCommentsWithParam(userId,postId);

    }

    @PostMapping
    public Comment createOneComment(@RequestBody CommentCreateRequest requestComment){
        return commentService.createOneComment(requestComment);
    }

    @GetMapping("/{commentId}")
    public Comment getOneComment(@PathVariable Long commentId){
        return commentService.getOneCommentById(commentId);
    }

    @PutMapping("/{commentId}")
    public Comment updateOneComment(@PathVariable Long commentId, @RequestBody CommentUpdateRequest commentUpdate){
        return commentService.updateOneCommentById(commentId, commentUpdate);
    }

    @DeleteMapping("/{commentId}")
    public void deleteOneComment(@PathVariable Long commentId){
        commentService.deleteOneCommentById(commentId);
    }


}
