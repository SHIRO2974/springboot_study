package com.korit.springboot_study.ioc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class classA {

    @Autowired
    private ClassD d;   // ClassA 가 생성될 때, ClassD 가 자동으로 주입된다

    public void classCallA() {

        System.out.println("Class A 메서드 호출");
    }
}
