package com.korit.springboot_study.dto.request.study.Book;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReqSearchBookDto {

    @ApiModelProperty(value = "도서명", example = "작별하지않는다", required = false)
    private String bookName = "";
}
