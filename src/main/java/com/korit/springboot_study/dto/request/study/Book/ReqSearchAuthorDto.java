package com.korit.springboot_study.dto.request.study.Book;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReqSearchAuthorDto {

    @ApiModelProperty(value = "저자명", example = "이재현", required = false)
    private String keyword;
}
