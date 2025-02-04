package com.korit.springboot_study.ioc;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration  // 설정 객체이기 때문에 우선순위가 제일 높다
public class ConfigA {

    @Bean   // 호출하지 않아도 실행
    public ClassD call() {  // 메소드명이 컴포넌트명이 된다.

        System.out.println("configA call");
        return new ClassD();    // 제일 먼저 생성 된다.(생성자의 파라미터를 다르게 줘야할 때 사용한다)
    }
}
