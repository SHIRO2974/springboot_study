package com.korit.springboot_study.service;

import com.korit.springboot_study.dto.request.study.ReqCreatePostDto;
import com.korit.springboot_study.entity.study.Post;
import com.korit.springboot_study.repository.PostRepository;
import org.apache.ibatis.javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;
import java.util.List;

@Service
public class PostService {

    @Autowired
    private PostRepository postRepository;

    @Transactional(rollbackFor = Exception.class)
    public Post createPost(ReqCreatePostDto reqCreatePostDto) {

        return postRepository.savePost(reqCreatePostDto.toPost()).get();
    }

    public Post getPostById(int postId) throws NotFoundException {

        return postRepository.findById(postId)
                .orElseThrow(() -> new NotFoundException("존재하지 않는 게시글입니다."));
    }

    public List<Post> getAllPostsByKeywordContaining(int page, int size, String keyword) throws NotFoundException {

        int startIndex = (page - 1) * size;
        List<Post> posts = postRepository.findAllByKeywordContaining(startIndex, size, keyword)
                .orElseThrow(() -> new NotFoundException("검색된 게시글이 없습니다."));
        return posts;
    }
}
