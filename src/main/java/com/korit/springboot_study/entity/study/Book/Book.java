package com.korit.springboot_study.entity.study.Book;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Book {

    private int bookId;
    private String bookName;
    private int authorId;
    private int isbn;
    private int categoryId;
    private int publisherId;
    private String bookImgUrl;

    private Author author;
    private Category category;
    private Publisher publisher;
}
