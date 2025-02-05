package com.korit.springboot_study.controller;

import com.korit.springboot_study.dto.request.study.ReqAddInstructorDto;
import com.korit.springboot_study.dto.request.study.ReqAddMajorDto;
import com.korit.springboot_study.dto.response.common.SuccessResponseDto;
import com.korit.springboot_study.entity.study.Instructor;
import com.korit.springboot_study.entity.study.Major;
import com.korit.springboot_study.service.InstructorStudyService;
import com.korit.springboot_study.service.StudentStudyService;
import io.swagger.annotations.ApiOperation;
import org.apache.ibatis.javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class StudentStudyController {

    @Autowired
    private StudentStudyService studentStudyService;

    @Autowired
    private InstructorStudyService instructorStudyService;

    @GetMapping("/api/study/majors")
    @ApiOperation(value = "학과 전체 조회")
    public ResponseEntity<SuccessResponseDto<List<Major>>> getMajors() throws NotFoundException {

        return ResponseEntity.ok().body(studentStudyService.getMajorsAll());
    }

    @GetMapping("/api/study/instructors")
    @ApiOperation(value = "교수 전체 조회")
    public ResponseEntity<SuccessResponseDto<List<Instructor>>> getInstructor() throws NotFoundException {

        return ResponseEntity.ok().body(instructorStudyService.getInstructorAll());
    }

    @PostMapping("/api/study/major")
    @ApiOperation(value = "학과 추가")
    public ResponseEntity<SuccessResponseDto<?>> addMajor(@RequestBody ReqAddMajorDto reqAddMajorDto) {

        System.out.println(reqAddMajorDto);
        return ResponseEntity.ok().body(studentStudyService.addMajor(reqAddMajorDto));  // 추가한 학과 데이터를 리턴해준다
    }

    @PostMapping("/api/study/instructor")
    @ApiOperation(value = "교수 추가")
    public ResponseEntity<SuccessResponseDto<?>> addInstructor(@RequestBody ReqAddInstructorDto instructorDto) {

        return ResponseEntity.ok().body(instructorStudyService.addInstructor(instructorDto));
    }

}
