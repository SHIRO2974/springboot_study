package com.korit.springboot_study.controller.Advice;

import com.korit.springboot_study.dto.response.common.BodRequestResponseDto;
import com.korit.springboot_study.dto.response.common.NotFoundResponseDto;
import com.korit.springboot_study.exception.CustomDuplicateKeyException;
import org.apache.ibatis.javassist.NotFoundException;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice   // 전역 예외 처리 어노테이션
public class GlobalRestControllerAdvice {   // 예외가 발생하면 Advice 로 이동

    @ExceptionHandler(value = NotFoundException.class)
    public ResponseEntity<NotFoundResponseDto<?>> notFound(NotFoundException e) {   // 데이터가 없다면

        return ResponseEntity.status(404).body(new NotFoundResponseDto<>(e.getMessage()));  // 예외 발생 응답
    }

    @ExceptionHandler(value = CustomDuplicateKeyException.class)
    public ResponseEntity<BodRequestResponseDto<?>> duplicateKey(CustomDuplicateKeyException e) {   // 중복 데이터가 있다면

        return ResponseEntity.status(400).body(new BodRequestResponseDto<>(e.getErrors()));  // 예외 발생 응답
    }
}
