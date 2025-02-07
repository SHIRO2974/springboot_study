package com.korit.springboot_study.dto.request.study.Book;

import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReqSearchCategoryDto {


    private String keyword;
}
