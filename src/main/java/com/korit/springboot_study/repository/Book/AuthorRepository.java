package com.korit.springboot_study.repository.Book;

import com.korit.springboot_study.entity.study.Book.Author;
import com.korit.springboot_study.exception.CustomDuplicateKeyException;
import com.korit.springboot_study.mapper.Book.AuthorMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class AuthorRepository {

    @Autowired
    private AuthorMapper authorMapper;

    public Optional<List<Author>> findAllByNameContaining(String authorName) {

        return authorMapper.selectAllByNameContaining(authorName).isEmpty()
                ? Optional.empty() : Optional.of(authorMapper.selectAllByNameContaining(authorName));
    }

    public Optional<Author> save(Author author) {

        try {

            authorMapper.insert(author);
        } catch (DuplicateKeyException e) {

            throw new CustomDuplicateKeyException("이미 존재하는 저자명 입니다.");
        }
        return Optional.of(author);
    }
}
