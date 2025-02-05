package com.korit.springboot_study.service;

import com.korit.springboot_study.dto.request.study.ReqAddInstructorDto;
import com.korit.springboot_study.dto.response.common.SuccessResponseDto;
import com.korit.springboot_study.entity.study.Instructor;
import com.korit.springboot_study.repository.InstructorStudyRepository;
import org.apache.ibatis.javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class InstructorStudyService {

    @Autowired
    private InstructorStudyRepository instructorStudyRepository;

    public SuccessResponseDto<List<Instructor>> getInstructorAll() throws NotFoundException {

        List<Instructor> foundInstructors = instructorStudyRepository.findAllInstructor()
                .orElseThrow(() -> new NotFoundException("검색한 교수가 존재하지 않습니다."));

        return new SuccessResponseDto<>(foundInstructors);
    }

    @Transactional(rollbackFor = Exception.class)
    public SuccessResponseDto<Instructor> addInstructor(ReqAddInstructorDto reqAddInstructorDto) throws DuplicateKeyException {

        return new SuccessResponseDto<>(
                instructorStudyRepository.saveInstructor(new Instructor(0, reqAddInstructorDto.getInstructorName()))
                        .orElseThrow()
        );
    }
}
