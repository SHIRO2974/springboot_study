package com.korit.springboot_study.repository;

import com.korit.springboot_study.entity.study.Book;
import com.korit.springboot_study.mapper.BookMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class BookRepository {

    @Autowired
    private BookMapper bookMapper;

    public Optional<List<Book>> findAllBook() {

        List<Book> foundBooks = bookMapper.selectBookAll();
        return foundBooks.isEmpty() ? Optional.empty() : Optional.of(foundBooks);
    }
}
