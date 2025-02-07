package com.korit.springboot_study.controller.Book;

import com.korit.springboot_study.dto.response.common.SuccessResponseDto;
import com.korit.springboot_study.entity.study.Book.Category;
import com.korit.springboot_study.service.CategoryService;
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
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @GetMapping("/api/study/category")
    @ApiOperation(value = "장르 전체 조회")
    public ResponseEntity<SuccessResponseDto<List<Category>>> getAllCategorys() throws NotFoundException {

        return ResponseEntity.ok().body(categoryService.getCategoryAll());
    }
}
