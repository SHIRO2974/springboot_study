package com.korit.springboot_study.dto.request.study.Book;

import com.korit.springboot_study.entity.study.Book.Author;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.Pattern;

@Data
public class ReqAddAuthorDto {

    @ApiModelProperty(value = "저자명", example = "이재현", required = true)
    @Pattern(regexp = "^[가-힣0-9\\s]+$", message = "입력값은 한글, 숫자, 띄어쓰기만 포함할 수 있습니다.")
    private String authorName;

    public Author toAuthor() {

        return Author.builder()
                .authorName(authorName)
                .build();
    }
}
