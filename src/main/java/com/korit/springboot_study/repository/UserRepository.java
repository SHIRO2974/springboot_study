package com.korit.springboot_study.repository;

import com.korit.springboot_study.entity.study.User;
import com.korit.springboot_study.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class UserRepository {

    @Autowired
    private UserMapper userMapper;

    public Optional<User> findByUsername(String username) {

        // 조회를 했을 때
        return Optional.ofNullable(userMapper.selectByUserName(username));
    }

    public Optional<User> save(User user) {

        try {

        userMapper.insertUser(user);

        } catch (DuplicateKeyException e) {

            return Optional.empty();
        }
        return Optional.of(user);
    }
}
