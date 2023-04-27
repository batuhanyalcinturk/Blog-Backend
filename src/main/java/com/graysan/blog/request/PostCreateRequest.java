package com.graysan.blog.request;

import lombok.Data;

@Data
public class PostCreateRequest {
    Long id;
    String text;
    String summary;
    String title;
    Long categoryId;
    Long userId;

}
