package com.korit.springboot_study.controller;

import com.korit.springboot_study.dto.response.common.SuccessResponseDto;
import com.korit.springboot_study.entity.study.Book;
import com.korit.springboot_study.entity.study.Publisher;
import com.korit.springboot_study.service.BookService;
import com.korit.springboot_study.service.PublisherService;
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
public class PublisherController {

    @Autowired
    private PublisherService publisherService;

    @GetMapping("/api/study/publisher")
    @ApiOperation(value = "출판사 전체 조회")
    public ResponseEntity<SuccessResponseDto<List<Publisher>>> getAllPublisher() throws NotFoundException {

        return ResponseEntity.ok().body(publisherService.getPublisherAll());
    }
}
