package com.korit.springboot_study.dto.request.study;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(description = "학생정보 조회 학습 DTO")
public class ReqStudentDto {

    @ApiModelProperty(value = "학생이름", example = "이재현")
    private String name;

    @ApiModelProperty(value = "학생나이", example = "24")
    private int age;
}
