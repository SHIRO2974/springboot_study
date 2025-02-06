package com.korit.springboot_study.service;

import com.korit.springboot_study.dto.response.common.SuccessResponseDto;
import com.korit.springboot_study.entity.study.Book;
import com.korit.springboot_study.repository.BookRepository;
import org.apache.ibatis.javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {

    @Autowired
    private BookRepository bookRepository;

    public SuccessResponseDto<List<Book>> getBookAll() throws NotFoundException {

        List<Book> foundBooks = bookRepository.findAllBook()
                .orElseThrow(() -> new NotFoundException("책 데이터가 존재하지 않습니다"));

        return new SuccessResponseDto<>(foundBooks);
    }
}
