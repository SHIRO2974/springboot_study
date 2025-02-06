package com.korit.springboot_study.dto.request.study;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.Pattern;

@Data
@ApiModel(value = "학과 수정")
public class ReqUpdateMajorDto {

    @ApiModelProperty(value = "학과명", example = "컴퓨터공학과", required = true)
    @Pattern(regexp = "^[가-힣]+$", message = "학과명은 한글로만 입력 가능합니다. 공백이나 다른 문자는 포함할 수 없습니다.")
    private String majorName;
}
