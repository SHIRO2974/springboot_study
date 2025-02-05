package com.korit.springboot_study.mapper;

import com.korit.springboot_study.entity.study.Instructor;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface InstructorStudyMapper {

    List<Instructor> selectInstructorsAll();

    int insertInstructor(Instructor instructor);
}
