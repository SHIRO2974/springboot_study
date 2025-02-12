package com.korit.springboot_study.service;

import com.korit.springboot_study.aspect.PrintParamsAOP;
import com.korit.springboot_study.dto.request.study.ReqCreatePostDto;
import com.korit.springboot_study.entity.study.Post;
import com.korit.springboot_study.entity.study.PostLike;
import com.korit.springboot_study.exception.CustomDuplicateKeyException;
import com.korit.springboot_study.repository.PostLikeRepository;
import com.korit.springboot_study.repository.PostRepository;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StopWatch;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

@Service
@Slf4j
public class PostService {

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private PostLikeRepository postLikeRepository;

    @Transactional(rollbackFor = Exception.class)
    public Post createPost(ReqCreatePostDto reqCreatePostDto) {

        return postRepository.savePost(reqCreatePostDto.toPost()).get();
    }

    @PrintParamsAOP
    public Post getPostById(int postId) throws NotFoundException {  // Post ID 로 게시글 찾기

        Post post = postRepository.findById(postId) // 사용자가 입력한 Post Id 와 일치하는지 확인
                .orElseThrow(() -> new NotFoundException("존재하지 않는 게시글입니다."));
        return post;
    }

    @PrintParamsAOP
    public List<Post> getAllPostsByKeywordContaining(int page, int size, String keyword) throws NotFoundException {

        int startIndex = (page - 1) * size;
        List<Post> posts = postRepository.findAllByKeywordContaining(startIndex, size, keyword)
                .orElseThrow(() -> new NotFoundException("검색된 게시글이 없습니다."));
        return posts;
    }

    public Boolean likePost(int postId, int userId) throws Exception {

        PostLike postLike = PostLike.builder()
                .postId(postId)
                .userId(userId)
                .build();

        return postLikeRepository.save(postLike)
                .orElseThrow(() -> new CustomDuplicateKeyException("", Map.of("message","해당 게시글을 이미 like 처리하였습니다.")));
    }
}
