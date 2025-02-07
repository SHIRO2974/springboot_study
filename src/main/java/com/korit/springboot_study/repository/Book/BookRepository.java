package com.korit.springboot_study.repository.Book;

import com.korit.springboot_study.entity.study.Book.Book;
import com.korit.springboot_study.exception.CustomDuplicateKeyException;
import com.korit.springboot_study.mapper.Book.BookMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class BookRepository {

    @Autowired
    private BookMapper bookMapper;

    public Optional<List<Book>> findAllBook(String bookName) {

        List<Book> foundBooks = bookMapper.selectAllByBookNameContaining(bookName);
        return foundBooks.isEmpty() ? Optional.empty() : Optional.of(foundBooks);
    }

    public Optional<Book> saveBook(Book book) {

        try {

            bookMapper.insertBook(book);

        } catch (DuplicateKeyException e) {

            throw new CustomDuplicateKeyException("이미 존재하는 책 이름 입니다");
        }
        return Optional.of(book);
    }
}
