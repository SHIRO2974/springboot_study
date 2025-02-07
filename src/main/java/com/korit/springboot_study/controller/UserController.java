package com.korit.springboot_study.controller;

import com.korit.springboot_study.dto.ReqAddUserDto;
import com.korit.springboot_study.dto.response.common.SuccessResponseDto;
import com.korit.springboot_study.entity.study.User;
import com.korit.springboot_study.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Pattern;

@RestController
@Validated
@Api(tags = "사용자 정보 API")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/api/user/username/duplication")
    public ResponseEntity<SuccessResponseDto<Boolean>> duplicateUsername(
            @RequestParam @Pattern(regexp = "^[a-zA-Z][a-zA-Z0-9_-]{2,19}$",
            message = "username 은 첫 글자가 알파벳이어야 하며, 길이는 3자 이상, 20자 이하여야 합니다.")String username) {

        return ResponseEntity.ok().body(new SuccessResponseDto<>(userService.duplicateUsername(username)));
    }

    @PostMapping("/api/user")
    @ApiOperation(value = "사용자 추가")
    public ResponseEntity<SuccessResponseDto<User>> addUser(@Valid @RequestBody ReqAddUserDto reqAddUserDto)throws MethodArgumentNotValidException {

        return ResponseEntity.ok().body(new SuccessResponseDto<>(userService.addUser(reqAddUserDto)));
    }
}
