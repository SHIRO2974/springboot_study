package com.korit.springboot_study.dto.request.study.Book;

import com.korit.springboot_study.entity.study.Book.Category;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.Pattern;

@Data
public class ReqAddCategoryDto {

    @ApiModelProperty(value = "카테고리 이름", example = "교육", required = true)
    @Pattern(regexp = "^[가-힣0-9\\s]+$", message = "입력값은 한글, 숫자, 띄어쓰기만 포함할 수 있습니다.")
    private String categoryName;

    public Category toCategory() {

        return Category.builder()
                .categoryName(categoryName)
                .build();
    }
}
