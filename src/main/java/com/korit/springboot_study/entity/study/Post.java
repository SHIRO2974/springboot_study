package com.korit.springboot_study.entity.study;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Post {

    private int postId;
    private int userId;
    private String title;
    private String content;
    private LocalDateTime createdTime;
    private LocalDateTime updatedTime;

    private int likeCount;

    private User user;
}
