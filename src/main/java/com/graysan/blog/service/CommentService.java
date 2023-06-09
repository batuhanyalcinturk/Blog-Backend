package com.graysan.blog.service;

import com.graysan.blog.entities.Comment;
import com.graysan.blog.entities.Post;
import com.graysan.blog.entities.User;
import com.graysan.blog.repos.CommentRepository;
import com.graysan.blog.request.CommentCreateRequest;
import com.graysan.blog.request.CommentUpdateRequest;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;

@Service
public class CommentService {
    private final CommentRepository commentRepository;
    private final UserService userService;
    private final PostService postService;

    public CommentService(CommentRepository commentRepository, UserService userService, PostService postService) {
        this.commentRepository = commentRepository;
        this.userService = userService;
        this.postService = postService;
    }

    public List<Comment> getAllCommentsWithParam(Optional<Long> userId, Optional<Long> postId) {
        if (userId.isPresent() && postId.isPresent()) {
            return this.commentRepository.findByUserIdAndPostId(userId.get(), postId.get());
        } else if (userId.isPresent()) {
            return this.commentRepository.findByUserId(userId.get());
        } else {
            if (postId.isPresent()) {
                List<Comment> byPostId = this.commentRepository.findByPostId(postId.get());
                if (byPostId==null || byPostId.isEmpty()){
                    List<Comment> responseList = new ArrayList<>();
                    Comment response = new Comment();
                    response.setText("There is no comment here");
                    responseList.add(response);
                    return responseList;
                }
                return byPostId;
            } else {
                return this.commentRepository.findAll();
            }
        }
    }

    public Comment getOneCommentById(Long commentId) {
        List<Comment> byId = this.commentRepository.findByPostId(commentId);
        if (byId.isEmpty()){
            Comment response = new Comment();
            response.setText("There is no comment here !!!!");
            return response;
        }
        return byId.get(0);
    }

    public Comment createOneComment(CommentCreateRequest requestComment) {
        User user = this.userService.getOneUserById(requestComment.getUserId());
        Post post = this.postService.getOnePostById(requestComment.getPostId());
        if (user != null && post != null) {
            Comment commentToSave = new Comment();
            commentToSave.setId(requestComment.getId());
            commentToSave.setPost(post);
            commentToSave.setUser(user);
            commentToSave.setText(requestComment.getText());
            return this.commentRepository.save(commentToSave);
        } else {
            return null;
        }
    }

    public Comment updateOneCommentById(Long commentId, CommentUpdateRequest commentUpdate) {
        Optional<Comment> comment = this.commentRepository.findById(commentId);
        if (comment.isPresent()) {
            Comment commentToUpdate = comment.get();
            commentToUpdate.setText(commentUpdate.getText());
            return this.commentRepository.save(commentToUpdate);
        } else {
            return null;
        }
    }

    public void deleteOneCommentById(Long commentId) {
        this.commentRepository.deleteById(commentId);
    }
}
