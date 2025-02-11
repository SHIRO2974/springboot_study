package com.korit.springboot_study.controller;

import com.korit.springboot_study.dto.request.study.ReqCreatePostDto;
import com.korit.springboot_study.dto.response.common.SuccessResponseDto;
import com.korit.springboot_study.entity.study.Post;
import com.korit.springboot_study.service.PostService;
import io.swagger.annotations.Api;
import org.apache.ibatis.javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@Validated
@Api(tags = "게시글 API")
public class PostController {

    @Autowired
    private PostService postService;

    @PostMapping("/api/post")
    public ResponseEntity<SuccessResponseDto<Post>> createPost(@RequestBody ReqCreatePostDto reqCreatePostDto) {

        return ResponseEntity.created(URI.create("")).body(new SuccessResponseDto<>(postService.createPost(reqCreatePostDto)));
    }

    @GetMapping("/api/post/{postId}")
    public ResponseEntity<SuccessResponseDto<Post>> getPost(@Valid @PathVariable int postId) throws NotFoundException {

        return ResponseEntity.ok().body(new SuccessResponseDto<>(postService.getPostById(postId)));
    }

    @GetMapping("/api/posts")
    public ResponseEntity<SuccessResponseDto<List<Post>>> getPosts (
            @RequestParam int page,
            @RequestParam int size, // 몇 개의 게시글이 보여지는지
            @RequestParam(required = false, defaultValue = "") String keyword) throws NotFoundException {

        return ResponseEntity.ok().body(new SuccessResponseDto<>(postService.getAllPostsByKeywordContaining(page, size, keyword)));
    }

    @PostMapping("/api/post/{postId}/like")
    public ResponseEntity<SuccessResponseDto<Post>> likePost(@PathVariable int postId) {

        return ResponseEntity.ok().body(new SuccessResponseDto<>(null));
    }

    @DeleteMapping("/api/post/{postId}/like")
    public ResponseEntity<SuccessResponseDto<Post>> dislikePost(@PathVariable int postId) {

        return ResponseEntity.ok().body(new SuccessResponseDto<>(null));
    }
}
