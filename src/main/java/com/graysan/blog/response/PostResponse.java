package com.graysan.blog.response;

import com.graysan.blog.entities.Like;
import com.graysan.blog.entities.Post;
import lombok.Data;

import java.util.List;

@Data
public class PostResponse {

    Long id;
    Long userId;
    String userName;
    String text;
    String title;
    String summary;
    List<LikeResponse> postLikes;

    public PostResponse(Post entity, List<LikeResponse> likes){
        this.id = entity.getId();
        this.userId = entity.getUser().getId();
        this.userName = entity.getUser().getUserName();
        this.title = entity.getTitle();
        this.text = entity.getText();
        this.summary = entity.getSummary();
        this.postLikes = likes;
    }

}
