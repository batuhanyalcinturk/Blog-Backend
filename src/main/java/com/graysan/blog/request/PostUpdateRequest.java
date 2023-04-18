package com.graysan.blog.request;

import lombok.Data;

@Data
public class PostUpdateRequest {
    String title;
    String text;
}
