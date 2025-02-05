package com.korit.springboot_study.repository;

import com.korit.springboot_study.entity.study.Major;
import com.korit.springboot_study.exception.CustomDuplicateKeyException;
import com.korit.springboot_study.mapper.StudentStudyMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository // Repository 컴포넌트 생성
public class StudentStudyRepository {

    @Autowired
    private StudentStudyMapper studentStudyMapper;

    public Optional<List<Major>> findAllMajor() {

        List<Major> foundMajors = studentStudyMapper.selectMajorsAll(); // Major 들을 DB 에서 조회

        if (foundMajors.isEmpty()) {    // 조회된 Major 목록이 비어있는지 확인

            return Optional.empty();    // 비어있다면 결과가 없음을 나타냄
        }
        return Optional.ofNullable(foundMajors);    // 비어있지 않다면 조회된 Major 반환

//      return foundMajors.isEmpty() ? Optional.empty() : Optional.of(foundMajors); 삼항 연산
    }

    public Optional<Major> saveMajor(Major major) throws DuplicateKeyException {

        try {

        studentStudyMapper.insertMajor(major);  // 성공 횟수 반환

        } catch (DuplicateKeyException e) {

            // 생성 할 때 만들어 준다
            throw new CustomDuplicateKeyException(e.getMessage(), Map.of("majorName", "이미 존재하는 학과명입니다."));
        }
        return Optional.ofNullable(new Major(major.getMajorId(), major.getMajorName()));
    }
}
