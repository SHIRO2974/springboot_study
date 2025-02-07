package com.korit.springboot_study.controller.Book;

import com.korit.springboot_study.dto.request.study.Book.ReqAddAuthorDto;
import com.korit.springboot_study.dto.request.study.Book.ReqSearchAuthorDto;
import com.korit.springboot_study.dto.response.common.SuccessResponseDto;
import com.korit.springboot_study.entity.study.Book.Author;
import com.korit.springboot_study.service.AuthorService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.ibatis.javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@Validated
@Api(tags = "저자 정보 API")
public class AuthorController {

    @Autowired
    private AuthorService authorService;

    @GetMapping("/api/book/author")
    @ApiOperation(value = "저자 조회")
    public ResponseEntity<SuccessResponseDto<List<Author>>> searchAuthor(@ModelAttribute ReqSearchAuthorDto reqSearchAuthorDto) throws NotFoundException {

        return ResponseEntity.ok().body(new SuccessResponseDto<>(authorService.getAuthors(reqSearchAuthorDto)));
    }

    @PostMapping("/api/book/addAuthor")
    @ApiOperation(value = "저자 추가")
    public ResponseEntity<SuccessResponseDto<Author>> addAuthor(@Valid @RequestBody ReqAddAuthorDto reqAddAuthorDto) throws NotFoundException {

        return ResponseEntity.ok().body(new SuccessResponseDto<>(authorService.addAuthor(reqAddAuthorDto)));
    }
}
