package com.korit.springboot_study.dto.request.study;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value = "교수 추가 DTO")
public class ReqAddInstructorDto {

    @ApiModelProperty(value = "교수명", example = "김교수", required = true)
    private String instructorName;
}
