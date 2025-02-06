package com.korit.springboot_study.controller;

import com.korit.springboot_study.dto.request.study.ReqAddInstructorDto;
import com.korit.springboot_study.dto.request.study.ReqAddMajorDto;
import com.korit.springboot_study.dto.request.study.ReqUpdateMajorDto;
import com.korit.springboot_study.dto.response.common.SuccessResponseDto;
import com.korit.springboot_study.entity.study.Instructor;
import com.korit.springboot_study.entity.study.Major;
import com.korit.springboot_study.service.InstructorStudyService;
import com.korit.springboot_study.service.StudentStudyService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.ibatis.javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import java.util.List;

@RestController
@Validated  // 유효성 검사 활성화
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
    // Valid: 요청이 들어왔을 때 유효성 검사 해준다
    public ResponseEntity<SuccessResponseDto<Major>> addMajor(@Valid @RequestBody ReqAddMajorDto reqAddMajorDto) throws MethodArgumentNotValidException {

        System.out.println(reqAddMajorDto);
        return ResponseEntity.ok().body(studentStudyService.addMajor(reqAddMajorDto));  // 추가한 학과 데이터를 리턴해준다
    }

    @PostMapping("/api/study/instructor")
    @ApiOperation(value = "교수 추가")
    public ResponseEntity<SuccessResponseDto<Instructor>> addInstructor(@RequestBody ReqAddInstructorDto instructorDto) {

        return ResponseEntity.ok().body(instructorStudyService.addInstructor(instructorDto));
    }

    @PutMapping("/api/study/major/{majorId}")
    @ApiOperation(value = "학과 수정")
    public ResponseEntity<SuccessResponseDto<?>> updateMajor(
            @ApiParam(value = "학과 ID", example = "1", required = true)
            @PathVariable @Min(value = 1, message = "학과 ID는 1이상의 정수여야 합니다") int majorId,
            @Valid @RequestBody ReqUpdateMajorDto reqUpdateMajorDto) throws NotFoundException, MethodArgumentNotValidException {

        return ResponseEntity.ok().body(studentStudyService.updateMajor(majorId,reqUpdateMajorDto));
    }
}
