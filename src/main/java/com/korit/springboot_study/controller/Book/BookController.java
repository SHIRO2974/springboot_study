package com.korit.springboot_study.controller.Book;

import com.korit.springboot_study.dto.request.study.Book.ReqAddBookDto;
import com.korit.springboot_study.dto.request.study.Book.ReqSearchBookDto;
import com.korit.springboot_study.dto.response.common.SuccessResponseDto;
import com.korit.springboot_study.entity.study.Book.Book;
import com.korit.springboot_study.service.BookService;
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
@Api(tags = "도서 API")
public class BookController {

    @Autowired
    private BookService bookService;

    @GetMapping("/api/study/book")
    @ApiOperation(value = "도서 조회")
    public ResponseEntity<SuccessResponseDto<List<Book>>> getAllBooks(@ModelAttribute ReqSearchBookDto reqSearchBookDto) throws NotFoundException {

        return ResponseEntity.ok().body(new SuccessResponseDto<>(bookService.getBooks(reqSearchBookDto)));
    }

    @ApiOperation(value = "도서 추가")
    @PostMapping("/api/study/addBook")
    public ResponseEntity<SuccessResponseDto<Book>> addBook(@Valid @RequestBody ReqAddBookDto reqAddBookDto) {

        return ResponseEntity.ok().body(new SuccessResponseDto<>(bookService.addBook(reqAddBookDto)));
    }
}
