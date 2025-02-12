package com.korit.springboot_study.dto.request.study;

import com.korit.springboot_study.entity.study.Post;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ReqCreatePostDto {

    private int userId;
    private String title;
    private String content;

    public Post toPost() {

        return Post.builder()
                .userId(userId)
                .title(title)
                .content(content)
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .build();
    }
}
