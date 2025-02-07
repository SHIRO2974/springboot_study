package com.korit.springboot_study.service;

import com.korit.springboot_study.dto.request.study.Book.ReqAddAuthorDto;
import com.korit.springboot_study.dto.request.study.Book.ReqSearchAuthorDto;
import com.korit.springboot_study.dto.response.common.SuccessResponseDto;

import com.korit.springboot_study.entity.study.Book.Author;
import com.korit.springboot_study.repository.Book.AuthorRepository;
import org.apache.ibatis.javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthorService {

    @Autowired
    private AuthorRepository authorRepository;

    public List<Author> getAuthors(ReqSearchAuthorDto reqSearchAuthorDto) throws NotFoundException {

        return authorRepository.findAllByNameContaining(reqSearchAuthorDto.getKeyword())
                .orElseThrow(() -> new NotFoundException("조회된 저자가 없습니다"));
    }

    public Author addAuthor(ReqAddAuthorDto reqAddAuthorDto) throws NotFoundException {

        return authorRepository.save(reqAddAuthorDto.toAuthor())
                .get();
    }
}
