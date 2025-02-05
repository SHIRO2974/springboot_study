package com.korit.springboot_study.service;

import com.korit.springboot_study.dto.request.study.ReqAddMajorDto;
import com.korit.springboot_study.dto.response.common.NotFoundResponseDto;
import com.korit.springboot_study.dto.response.common.ResponseDto;
import com.korit.springboot_study.dto.response.common.SuccessResponseDto;
import com.korit.springboot_study.entity.study.Major;
import com.korit.springboot_study.repository.StudentStudyRepository;
import org.apache.ibatis.javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service    // Service 컴포넌트 생성
public class StudentStudyService {

    @Autowired
    private StudentStudyRepository studentStudyRepository;

    public SuccessResponseDto<List<Major>> getMajorsAll() throws NotFoundException{

       List<Major> foundMajors = studentStudyRepository.findAllMajor()  // Optional 타입
               .orElseThrow(() -> new NotFoundException("학과 데이터가 존재하지 않습니다.")); // 예외 발생

        return new SuccessResponseDto<>(foundMajors); // foundMajor 가 비어 있지 않다면 SuccessResponseDto 호출
    }

    @Transactional(rollbackFor = Exception.class)   // 예외가 터지면 롤백 (DB 에 영향을 주면 안되기 때문)
    public SuccessResponseDto<Major> addMajor(ReqAddMajorDto reqAddMajorDto) throws DuplicateKeyException {

        return new SuccessResponseDto<>(    // 중복된 데이터가 있다면 예외 발생 그렇지 않다면
                studentStudyRepository.saveMajor(new Major(0, reqAddMajorDto.getMajorName()))
                        .orElseThrow()
        );
    }
}
