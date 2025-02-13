package com.korit.springboot_study.controller.Advice;

import com.korit.springboot_study.dto.response.common.BodRequestResponseDto;
import com.korit.springboot_study.dto.response.common.NotFoundResponseDto;
import com.korit.springboot_study.exception.CustomDuplicateKeyException;
import org.apache.ibatis.javassist.NotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.naming.AuthenticationException;
import javax.validation.ConstraintViolationException;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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

    public ResponseEntity<BodRequestResponseDto<?>> validation(ConstraintViolationException e) {

        return ResponseEntity.status(400).body(new BodRequestResponseDto<>
                (e.getConstraintViolations()
                        .stream().map(constraintViolation -> Map.of(constraintViolation.getPropertyPath(), constraintViolation.getMessage()))
                        .collect(Collectors.toList())
                ));
    }

    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public ResponseEntity<BodRequestResponseDto<?>> validation(MethodArgumentNotValidException e) {

        List<Map<String, String>> errorMap = null;
        BindingResult bindingResult = e.getBindingResult();

        if (bindingResult.hasErrors()) {    // 에러들이 있는지 확인

            bindingResult.getFieldErrors().stream()
                    // 에러가 있으면, 필드 이름과 메시지를 리스트로 추가
                    .map(fieldError -> Map.of(fieldError.getField(), fieldError.getDefaultMessage()))
                    .collect(Collectors.toList());
        }
        return ResponseEntity.status(400).body(new BodRequestResponseDto<>(errorMap));
    }
    @ExceptionHandler(value = AuthenticationException.class)
    public ResponseEntity<BodRequestResponseDto<?>> signInError(AuthenticationException e) {
        return ResponseEntity.status(403).body(new BodRequestResponseDto<>(e.getMessage()));
    }

}
