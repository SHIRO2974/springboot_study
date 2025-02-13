package com.korit.springboot_study.service;

import com.korit.springboot_study.dto.request.study.ReqSignInDto;
import com.korit.springboot_study.dto.request.study.ReqSignupDto;
import com.korit.springboot_study.entity.study.User;
import com.korit.springboot_study.entity.study.UserRole;
import com.korit.springboot_study.exception.CustomDuplicateKeyException;
import com.korit.springboot_study.repository.UserRepository;
import com.korit.springboot_study.repository.UserRoleRepository;
import com.korit.springboot_study.security.JWT.JwtProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;

@Service
public class AuthenticationService {

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserRoleRepository userRoleRepository;

    @Autowired
    private JwtProvider jwtProvider;

    @Transactional(rollbackFor = Exception.class)
    public User signup(ReqSignupDto reqSignupDto) {

        User user = reqSignupDto.toUser(passwordEncoder);
        User saveUser = userRepository.save(user)
                .orElseThrow(() -> new CustomDuplicateKeyException("이미 존재하는 사용자 이름입니다.", Map.of("username","이미 존재하는 사용자입니다")));

        userRoleRepository.save(UserRole.builder()
                .userId(user.getUserId())
                .roleId(1)
                .build());
        return saveUser;
    }

    public String signIn(ReqSignInDto reqsignInDto) {

        String accessToken = null;

        // userName 확인
        User foundUser = userRepository
                .findByUsername(reqsignInDto.getUserName())
                .orElseThrow(() -> new UsernameNotFoundException("사용자 정보를 다시 확인하세요"));

        // password 확인
        if (!passwordEncoder.matches(reqsignInDto.getPassword(), foundUser.getPassword())) {

            throw new BadCredentialsException("사용자 정보를 다시 확인하세요");
        }

        // AccessToken 생성
        accessToken = jwtProvider.createAccessToken(foundUser);

        return accessToken;
    }
}
