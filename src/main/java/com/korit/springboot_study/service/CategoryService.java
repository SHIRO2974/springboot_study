package com.korit.springboot_study.service;

import com.korit.springboot_study.dto.response.common.SuccessResponseDto;
import com.korit.springboot_study.entity.study.Book.Category;
import com.korit.springboot_study.repository.Book.CategoryRepository;
import org.apache.ibatis.javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    public SuccessResponseDto<List<Category>> getCategoryAll() throws NotFoundException {

        List<Category> foundCategorys = categoryRepository.findAllCategory()
                .orElseThrow(() -> new NotFoundException("장르 데이터가 존재하지 않습니다"));

        return new SuccessResponseDto<>(foundCategorys);
    }
}
