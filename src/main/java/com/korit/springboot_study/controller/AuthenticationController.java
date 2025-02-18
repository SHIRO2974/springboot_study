package com.korit.springboot_study.controller;

import com.korit.springboot_study.dto.request.study.ReqSignInDto;
import com.korit.springboot_study.dto.request.study.ReqSignupDto;
import com.korit.springboot_study.dto.response.common.SuccessResponseDto;
import com.korit.springboot_study.entity.study.User;
import com.korit.springboot_study.service.AuthenticationService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@Api(tags = "계정 API")
@Validated
public class AuthenticationController {

    @Autowired
    private AuthenticationService authenticationService;

    @PostMapping("/api/auth/signup")
    @ApiOperation(value = "회원가입")
    public ResponseEntity<SuccessResponseDto<User>> signUp(@Valid @RequestBody ReqSignupDto reqsignupDto)throws MethodArgumentNotValidException {

        return ResponseEntity.ok().body(new SuccessResponseDto<>(authenticationService.signup(reqsignupDto)));
    }

    @PostMapping("/api/auth/signin")
    public ResponseEntity<SuccessResponseDto<String>> signIn(@Valid @RequestBody ReqSignInDto reqsigninDto)throws MethodArgumentNotValidException {

        return ResponseEntity.ok().body(new SuccessResponseDto<>(authenticationService.signIn(reqsigninDto)));
    }

    @PostMapping("/api/auth/logout")
    @ApiOperation(value = "로그아웃")
    public ResponseEntity<SuccessResponseDto<?>> logout() {

        return ResponseEntity.ok().body(new SuccessResponseDto<>(null));
    }
}
