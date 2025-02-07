package com.korit.springboot_study.mapper.Book;

import com.korit.springboot_study.entity.study.Book.Book;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface BookMapper {

    int insertBook(Book book);
    List<Book> selectAllByBookNameContaining(@Param(value = "bookName")String bookName);
}
