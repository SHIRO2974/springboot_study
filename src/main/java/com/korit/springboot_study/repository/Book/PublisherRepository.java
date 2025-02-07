package com.korit.springboot_study.repository.Book;
import com.korit.springboot_study.entity.study.Book.Publisher;
import com.korit.springboot_study.mapper.Book.PublisherMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class PublisherRepository {

    @Autowired
    private PublisherMapper publisherMapper;

    public Optional<List<Publisher>> findAllPublisher() {

        List<Publisher> foundBooks = publisherMapper.selectPublisherAll();
        return foundBooks.isEmpty() ? Optional.empty() : Optional.of(foundBooks);
    }
}
