package com.korit.springboot_study.mapper;

import com.korit.springboot_study.entity.study.Major;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper // Mapper 컴포넌트 생성
public interface StudentStudyMapper {

    List<Major> selectMajorsAll();

    int insertMajor(Major major);

    int updateMajor(Major major);
}
