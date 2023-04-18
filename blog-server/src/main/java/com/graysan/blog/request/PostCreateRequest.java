package com.graysan.blog.request;

import lombok.*;

@Data
public class PostCreateRequest {

    Long id;
    String text;
    String title;
    Long categoryId;
    Long userId;
}
