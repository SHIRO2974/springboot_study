package com.korit.springboot_study.service;

import com.korit.springboot_study.dto.response.common.SuccessResponseDto;
import com.korit.springboot_study.entity.study.Book.Publisher;
import com.korit.springboot_study.repository.Book.PublisherRepository;
import org.apache.ibatis.javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PublisherService {

    @Autowired
    private PublisherRepository publisherRepository;

    public SuccessResponseDto<List<Publisher>> getPublisherAll() throws NotFoundException {

        List<Publisher> foundPublisher = publisherRepository.findAllPublisher()
                .orElseThrow(() -> new NotFoundException("저자 데이터가 존재하지않습니다"));
        return new SuccessResponseDto<>(foundPublisher);
    }
}
