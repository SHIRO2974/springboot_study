package com.korit.springboot_study.config;

import com.korit.springboot_study.security.Filter.CustomAuthenticationFilter;
import com.korit.springboot_study.security.Filter.JwtAuthenticationFilter;
import com.korit.springboot_study.security.exception.CustomAuthenticationEntryPoint;
import com.korit.springboot_study.security.oauth2.OAuth2Service;
import com.korit.springboot_study.security.oauth2.OAuth2SuccessHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity  // Spring Security 를 활성화하는 어노테이션
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private OAuth2SuccessHandler oAuth2SuccessHandler;

    // OAuth2 로그인 사용자 정보 서비스
    @Autowired
    private OAuth2Service oauth2Service;

    // 인증 실패 처리 핸들러
    @Autowired
    private CustomAuthenticationEntryPoint customAuthenticationEntryPoint;

    // JWT 인증 필터
    @Autowired
    private JwtAuthenticationFilter jwtAuthenticationFilter;

    // 비밀번호 암호화를 위한 인코더
    @Bean
    public BCryptPasswordEncoder passwordEncoder() {

        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.cors();    // CORS(Cross-Origin Resource Sharing) 설정 활성화
        http.csrf().disable();  // CSRF(Cross-Site Request Forgery) 보호 비활성화
        http.httpBasic().disable(); // 기본 HTTP 인증 비활성화
        http.formLogin().disable(); // 기본 로그인 폼 비활성화 (커스텀 로그인 방식을 사용할 경우)
        http.sessionManagement()    // 세션을 사용하지 않음 (JWT 방식은 상태 없는 인증 방식이기 때문에)
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        // JWT 필터를 UsernamePasswordAuthenticationFilter 앞에 추가하여, 인증을 필터링
        http.addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);

        http.exceptionHandling().authenticationEntryPoint(customAuthenticationEntryPoint);

        // OAuth2 로그인 설정
        http.oauth2Login()
                .successHandler(oAuth2SuccessHandler)   // OAuth2 로그인 성공
                        .userInfoEndpoint() // 사용자 정보 엔드포인트 설정
                                .userService(oauth2Service);    // OAuth2 사용자 정보 서비스 설정

        // 인증절차 설정
        http.authorizeHttpRequests()
                .antMatchers(
                        "/swagger-ui/**",  // Swagger UI 관련 경로는 누구나 접근 가능
                        "/v2/api-docs/**", // API 문서 관련 경로는 누구나 접근 가능
                        "/v3/api-docs/**", // API 문서 관련 경로는 누구나 접근 가능
                        "/swagger-resources/**", // Swagger 리소스 관련 경로는 누구나 접근 가능
                        "/server/hc" // 서버 헤쉬 체크는 누구나 접근 가능
                )
                .permitAll()    // post/user 경로는 모든 사용자에게 허용
                .antMatchers("/api/auth/**")
                .permitAll()    // post/user 경로는 모든 사용자에게 허용
                .anyRequest()   // 그 외의 모든 요청
                .authenticated();   // 인증된 사용자만 허용
    }
}
