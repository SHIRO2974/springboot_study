package com.korit.springboot_study.dto.request.study;


import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.Pattern;

@Data
public class ReqSignInDto {

    @ApiModelProperty(value = "사용자이름", example = "user1234", required = true)
    @Pattern(regexp = "^[a-zA-Z][a-zA-Z0-9_-]{2,19}$", message = "username 은 첫 글자가 알파벳이어야 하며, 길이는 3자 이상, 20자 이하여야 합니다.")
    private String userName;

    @ApiModelProperty(value = "비밀번호", example = "user1234!", required = true)
    @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[!@#$%^&*()_+={}\\[\\]|\\\\:;,.<>?/-]).{8,20}$", message = "대문자, 소문자, 숫자, 특수문자가 각각 1개 이상 포함되어야 합니다.")
    private String password;
}
