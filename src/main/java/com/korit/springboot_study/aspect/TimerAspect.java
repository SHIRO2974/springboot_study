package com.korit.springboot_study.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

@Aspect // 공통 관심사를 처리하는 모듈을 정의하는 역할
@Component
public class TimerAspect {

    // 특정 실행 지점(join point)을 정의하고, 어드바이스가 실행될 메소드를 결정하는 역할
    @Pointcut("execution(* com.korit.springboot_study.service.PostService.*(..))")
    private void executionPointCut() {}

    @Pointcut("@annotation(com.korit.springboot_study.aspect.TimerAOP)")
    private void annotationPointCut() {}

    @Around("executionPointCut()||annotationPointCut()")   // 메소드 실행 전후에 실행되는 기능을 제공
    public Object around(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {

        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        Object result = proceedingJoinPoint.proceed();  // 실제 비즈니스 메소드를 실행하는 역할
        stopWatch.stop();
        System.out.println("메소드 실행시간: " + stopWatch.getTotalTimeSeconds()); // 메서드 실행 시간확인

        return result;
    }
}
