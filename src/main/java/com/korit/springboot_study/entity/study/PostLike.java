package com.korit.springboot_study.entity.study;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PostLike {

    private int postLikeId;
    private int postId;
    private int userId;
    private LocalDateTime createTime;

    private int likeCount;
}
