package com.korit.springboot_study.service;

import com.korit.springboot_study.dto.ReqAddUserDto;
import com.korit.springboot_study.entity.study.User;
import com.korit.springboot_study.entity.study.UserRole;
import com.korit.springboot_study.exception.CustomDuplicateKeyException;
import com.korit.springboot_study.repository.UserRepository;
import com.korit.springboot_study.repository.UserRoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    UserRoleRepository UserRoleRepository;

    public Boolean duplicateUsername(String username) {

        return userRepository.findByUsername(username).isPresent();
    }

    @Transactional(rollbackFor = Exception.class)
    public User addUser(ReqAddUserDto reqAddUserDto) {

        User saveUser = userRepository.save(reqAddUserDto.toUser()).orElseThrow(() ->
                new CustomDuplicateKeyException("",Map.of("username", "이미 사용중인 사용자이름입니다.")));

        UserRoleRepository.save(UserRole.builder()
                .userId(saveUser.getUserId())
                .roleId(1)
                .build()).orElseThrow(() -> new CustomDuplicateKeyException("SQL Error"));
        return saveUser;
    }


}
