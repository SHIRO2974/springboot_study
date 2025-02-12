package com.korit.springboot_study.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.CodeSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.security.CodeSigner;

@Aspect
@Component
public class PrintParamsAspect {

    private static final Logger log = LoggerFactory.getLogger(PrintParamsAspect.class);

    @Pointcut("@annotation(com.korit.springboot_study.aspect.PrintParamsAOP)")
    private void pointCut(){}

    @Around("pointCut()")
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable {

        CodeSignature codeSignature = (CodeSignature) joinPoint.getSignature();
        String[] paramNames = codeSignature.getParameterNames();    // 메소드의 파라미터 이름을 배열로 반환
        Object[] args = joinPoint.getArgs();    // 전달된 파라미터의 값을 배열 형태로 반환

        log.info("로그를 출력합니다."); // 메서드가 실행되면 로그 기록을 남긴다

       for (int i = 0; i < paramNames.length; i++) {    // 파라미터의 이름과 값을 순차적으로 비교

           System.out.println(paramNames[i] + ":" + args[i]);   // 매개변수의 이름과 값을 출력
       }

        Object result = joinPoint.proceed();

        return result;
    }
}
