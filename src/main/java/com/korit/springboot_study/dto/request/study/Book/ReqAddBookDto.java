package com.korit.springboot_study.dto.request.study.Book;

import com.korit.springboot_study.entity.study.Book.Book;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.Pattern;

@Data
@ApiModel(value = "책 추가 Dto")
public class ReqAddBookDto {

    @ApiModelProperty(value = "책 이름", example = "작별하지 않는다", required = true)
    @Pattern(regexp = "^[가-힣0-9\\s]+$", message = "입력값은 한글, 숫자, 띄어쓰기만 포함할 수 있습니다.")
    private String bookName;

    public Book toBook() {

        return Book.builder()
                .bookName(bookName)
                .build();
    }
}
