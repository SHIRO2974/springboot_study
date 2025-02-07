package com.korit.springboot_study.repository.Book;

import com.korit.springboot_study.entity.study.Book.Category;
import com.korit.springboot_study.mapper.Book.CategoryMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class CategoryRepository {

    @Autowired
    private CategoryMapper categoryMapper;

    public Optional<List<Category>> findAllCategory() {

        List<Category> foundCategory = categoryMapper.selectCategoryAll();
        return foundCategory.isEmpty() ? Optional.empty() : Optional.of(foundCategory);
    }

}
