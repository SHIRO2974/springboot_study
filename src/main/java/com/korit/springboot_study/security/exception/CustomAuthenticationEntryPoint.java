package com.korit.springboot_study.security.exception;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

@Component
public class CustomAuthenticationEntryPoint implements AuthenticationEntryPoint {

    @Autowired
    private ObjectMapper objectMapper;

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {

        System.out.println("인증 예외 발생");
        authException.printStackTrace();
        response.setStatus(403);    // 403 에러 발생
        response.setContentType("application/json");    // Json 으로 응답
        response.setCharacterEncoding("utf-8"); // 한글 인코딩
        response.getWriter().println(objectMapper.writeValueAsString(Map.of("error","인증이 필요합니다!")));    // 예외 응답 처리
    }
}
