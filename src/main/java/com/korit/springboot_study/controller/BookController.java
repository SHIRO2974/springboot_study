package com.korit.springboot_study.controller;

import com.korit.springboot_study.dto.response.common.SuccessResponseDto;
import com.korit.springboot_study.entity.study.Book;
import com.korit.springboot_study.service.BookService;
import io.swagger.annotations.ApiOperation;
import org.apache.ibatis.javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Validated
public class BookController {

    @Autowired
    private BookService bookService;

    @GetMapping("/api/study/book")
    @ApiOperation(value = "책 전체 조회")
    public ResponseEntity<SuccessResponseDto<List<Book>>> getAllBooks() throws NotFoundException {

        return ResponseEntity.ok().body(bookService.getBookAll());
    }
}
