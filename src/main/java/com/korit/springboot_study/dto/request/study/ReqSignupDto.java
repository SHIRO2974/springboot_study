package com.korit.springboot_study.dto.request.study;

import com.korit.springboot_study.entity.study.User;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Data
public class ReqSignupDto {

    @ApiModelProperty(value = "사용자이름", example = "user1234", required = true)
    @Pattern(regexp = "^[a-zA-Z][a-zA-Z0-9_-]{2,19}$", message = "username 은 첫 글자가 알파벳이어야 하며, 길이는 3자 이상, 20자 이하여야 합니다.")
    private String userName;

    @ApiModelProperty(value = "비밀번호", example = "user1234!", required = true)
    @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[!@#$%^&*()_+={}\\[\\]|\\\\:;,.<>?/-]).{8,20}$", message = "대문자, 소문자, 숫자, 특수문자가 각각 1개 이상 포함되어야 합니다.")
    private String password;

    @ApiModelProperty(value = "성명", example = "이재현", required = true)
    @Pattern(regexp = "^[가-힣]{2,5}$", message = "이름은 한글로 2자 이상 5자 이하로 입력해주세요.")
    private String name;

    @ApiModelProperty(value = "이메일", example = "user1234@gmail.com", required = true)
    @NotBlank(message = "이메일을 입력하세요.")
    @Email(message = "이메일 주소는 'example@domain.com'과 같은 형식이어야 합니다.")
    private String email;

    public User toUser(BCryptPasswordEncoder passwordEncoder) {

        return User.builder()
                .username(userName)
                .password(passwordEncoder.encode(password))
                .name(name)
                .email(email)
                .build();
    }
}
