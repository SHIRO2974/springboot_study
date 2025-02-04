package com.korit.springboot_study.ioc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class classB {

    @Qualifier("c1")   // 여러 개의 동일 타입 빈이 있을 때 어떤 빈을 주입할지 지정할 수 있는 어노테이션
    @Autowired // IoC 컨테이너에서 관리하는 빈을 자동으로 주입
    private ClassC c1;    // 변수명을 컴포넌트명으로 바꿔줘야 한다

    @Qualifier("c2")
    @Autowired
    private ClassC c2;

    public void classCallB() {

        System.out.println("Class B 메서드 호출");
    }
}
