package com.korit.springboot_study.mapper.Book;

import com.korit.springboot_study.entity.study.Book.Publisher;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface PublisherMapper {

    List<Publisher> selectPublisherAll();
}
