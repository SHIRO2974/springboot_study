package com.korit.springboot_study.repository;

import com.korit.springboot_study.entity.study.Instructor;
import com.korit.springboot_study.exception.CustomDuplicateKeyException;
import com.korit.springboot_study.mapper.InstructorStudyMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
public class InstructorStudyRepository {

    @Autowired
    private InstructorStudyMapper instructorStudyMapper;

    public Optional<List<Instructor>> findAllInstructor() {

        List<Instructor> foundInstructors = instructorStudyMapper.selectInstructorsAll();

        return foundInstructors.isEmpty() ? Optional.empty() : Optional.ofNullable(foundInstructors);
    }

    public Optional<Instructor> saveInstructor(Instructor instructor) throws DuplicateKeyException {

        try {

            instructorStudyMapper.insertInstructor(instructor);

        } catch (DuplicateKeyException e) {

            throw new CustomDuplicateKeyException(e.getMessage(), Map.of("instructorName", "이미 존재하는 교수명입니다."));
        }
        return Optional.ofNullable(new Instructor(instructor.getInstructorId(), instructor.getInstructorName()));
    }
}
