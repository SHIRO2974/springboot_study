package com.korit.springboot_study.config;

import com.korit.springboot_study.security.Filter.CustomAuthenticationFilter;
import com.korit.springboot_study.security.exception.CustomAuthenticationEntryPoint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private CustomAuthenticationEntryPoint customAuthenticationEntryPoint;

    @Autowired
    private CustomAuthenticationFilter customAuthenticationFilter;

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.httpBasic().disable();
        http.formLogin().disable();
        http.addFilterBefore(customAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
        // 인증절차 설정
        http.authorizeHttpRequests()
                .antMatchers("/api/post/**", "/api/user/**")
                .permitAll()    // post/user 경로는 모든 사용자에게 허용
                .anyRequest()   // 그 외의 모든 요청
                .authenticated()   // 인증된 사용자만 허용
                .and()
                .exceptionHandling()   // 인증 예외 발생 시
                .authenticationEntryPoint(customAuthenticationEntryPoint);  // 예외처리

    }
}
