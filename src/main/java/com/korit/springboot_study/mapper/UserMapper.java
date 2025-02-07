package com.korit.springboot_study.mapper;

import com.korit.springboot_study.entity.study.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper {

    User selectByUserName(String userName);
    int insertUser(User user);
}
