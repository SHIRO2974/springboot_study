package com.korit.springboot_study.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class HealthCheckController {

//  @CrossOrigin(value = )  // 특정 서버의 요청을 받는 어노테이션
    @GetMapping("/server/hc")
    public ResponseEntity<Map<String, String>> healthCheck() {

        return ResponseEntity.ok(Map.of("status", "UP"));
    }


}
