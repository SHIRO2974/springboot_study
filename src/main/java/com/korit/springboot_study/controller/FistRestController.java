package com.korit.springboot_study.controller;

import com.korit.springboot_study.dto.request.study.ReqStudentDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


@RestController // 데이터로 응답  ResponseBody 생략가능
@Api(tags = "REST API 수업")
public class FistRestController {


    @GetMapping("/api/hello")
    public Map<String, Object> hello(HttpServletRequest request) {

        String age = request.getParameter("age");
        String address = request.getParameter("address");

        System.out.println(age);
        System.out.println(address);
        return Map.of("name", "이재현");
    }

    @GetMapping("/api/hello2")
    public Map<String, Object> hello2(@RequestParam int age, @RequestParam String address) {

        System.out.println(age);
        System.out.println(address);
        return Map.of("name", "이재현");
    }

    @ApiOperation(value = "학생조회(일반 for 선형탐색)", notes = "일반 for 문을 사용하여 선형 탐색학습")
    @GetMapping("/api/student")
    public Map<String, Object> getStudent(@ApiParam(value = "조회할 학생 Index", required = true) @RequestParam int id) {

        List<Map<String, Object>> students = new ArrayList<>();
        students.add(Map.of("id", 1, "name", "아무개", "age", 24));
        students.add(Map.of("id", 2, "name", "아무배", "age", 25));
        students.add(Map.of("id", 3, "name", "아무새", "age", 26));
        students.add(Map.of("id", 4, "name", "아무재", "age", 27));

        int foundIndex = -1;

        for (int i = 0; i < students.size(); i++) {

            if ((Integer) students.get(i).get("id") == id) {    // id 가 Object 타입이기에 Integer 로 다운캐스팅

                foundIndex = i;
                break;
            }
        }

        if (foundIndex == -1) {

            return Map.of("error", "찾지 못했음");
        }
        return students.get(foundIndex);
    }

    @ApiOperation(value = "학생조회(향상된 for 선형탐색)", notes = "향상된 for 문을 사용하여 선형 탐색학습")
    @GetMapping("/api/student2")
    public Map<String, Object> getStudent2(@ApiParam(value = "조회할 학생 Index", required = true) @RequestParam int id) {

        List<Map<String, Object>> students = new ArrayList<>();
        students.add(Map.of("id", 1, "name", "아무개", "age", 24));
        students.add(Map.of("id", 2, "name", "아무배", "age", 25));
        students.add(Map.of("id", 3, "name", "아무새", "age", 26));
        students.add(Map.of("id", 4, "name", "아무재", "age", 27));

        Map<String, Object> foundStudent = null;

        for(Map<String, Object> map : students) {   // 향상된 for 문

            if ((Integer) map.get("id") == id) {

                break;
            }
        }

        if (foundStudent == null) {

            return Map.of("error", "찾지 못했음");
        }
        return foundStudent;
    }

    @ApiOperation(value = "학생조회(스트림 API 선형탐색)", notes = "스트림 API 를 사용하여 선형 탐색학습")
    @GetMapping("/api/student3")
    public Map<String, Object> getStudent3(@ApiParam(value = "조회할 학생 Index", required = true) @RequestParam int id) {

        List<Map<String, Object>> students = new ArrayList<>();
        students.add(Map.of("id", 1, "name", "아무개", "age", 24));
        students.add(Map.of("id", 2, "name", "아무배", "age", 25));
        students.add(Map.of("id", 3, "name", "아무새", "age", 26));
        students.add(Map.of("id", 4, "name", "아무재", "age", 27));

        Map<String, Object> responseData = students.stream()
               .filter(student -> (Integer) student.get("id") == id)
               .findFirst() // findFirst 에서 찾았다면
               .orElse(Map.of("error", "찾지 못했음"));

        return responseData;    // 대입
    }

    @GetMapping("/api/student4/{id}")
    // PathVariable: url 경로의 변수
    public Map<String, Object> getStudent4(
            @ApiParam(value = "학생 ID", required = true) @PathVariable int id, ReqStudentDto reqStudentDto) {

        return Map.of("id", id, "name", reqStudentDto.getName(), "age", reqStudentDto.getAge());
    }
}
