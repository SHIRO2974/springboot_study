package com.korit.springboot_study.service;

import com.korit.springboot_study.dto.request.study.Book.ReqAddBookDto;
import com.korit.springboot_study.dto.request.study.Book.ReqSearchBookDto;
import com.korit.springboot_study.entity.study.Book.Book;
import com.korit.springboot_study.exception.CustomDuplicateKeyException;
import com.korit.springboot_study.repository.Book.BookRepository;
import org.apache.ibatis.javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {

    @Autowired
    private BookRepository bookRepository;

    public List<Book> getBooks(ReqSearchBookDto reqSearchBookDto) throws NotFoundException {
        return bookRepository.findAllBook(reqSearchBookDto.getBookName())
                .orElseThrow(() -> new NotFoundException("조회된 도서가 없습니다."));
    }
    public Book addBook(ReqAddBookDto reqAddBookDto) {
        return bookRepository
                .saveBook(reqAddBookDto.toBook())
                .orElseThrow(() -> new CustomDuplicateKeyException("이미 존재하는 도서명입니다."));
    }
}
