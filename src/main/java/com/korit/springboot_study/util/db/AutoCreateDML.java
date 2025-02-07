package com.korit.springboot_study.util.db;

import com.korit.springboot_study.entity.study.Role;
import com.korit.springboot_study.mapper.RoleMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class AutoCreateDML implements CommandLineRunner {

    @Autowired
    private RoleMapper roleMapper;

    @Override
    public void run(String... args) throws Exception {

//      insertRole();   // 서버 실행 시 최초 한 번 insert 실행
    }

    private void insertRole() { // 서버가 실행될 때 최초에 한번 insert 가 일어난다

        List<String> roleNames = List.of("ROLE_USER", "ROLE_ADMIN", "ROLE_MANAGER");
        roleMapper.insertAll(roleNames.stream().map(name -> Role.builder()
                .roleName(name).build()).collect(Collectors.toList()));
    }
}
