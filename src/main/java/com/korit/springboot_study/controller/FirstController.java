package com.korit.springboot_study.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller // Controller 어노테이션 필수!

public class FirstController {

    @GetMapping("/mvc/hello")   //  주소창에 입력할 주소 (HandlerMapping)
    public String hello(Model model) {

        model.addAttribute("name", "이재현");  // model 주소에 name 키, 이재현 값을 model 에 추가
        System.out.println("hello 메소드 호출");
        return "/resource/templates/hello.html"; // 요청받은 경로를 리턴
    }
}
