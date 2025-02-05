package com.korit.springboot_study.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StudentStudyController {

    @GetMapping("/api/study/majors")
    public ResponseEntity<?> getMajors() {

        return ResponseEntity.ok().body(null);
    }
}
