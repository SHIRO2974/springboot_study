package com.korit.springboot_study.security.Filter;

import com.korit.springboot_study.repository.UserRepository;
import com.korit.springboot_study.security.JWT.JwtProvider;
import com.korit.springboot_study.security.principal.PrincipalUser;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@Component
public class JwtAuthenticationFilter implements Filter {

    @Autowired
    private JwtProvider jwtProvider;

    @Autowired
    private UserRepository userRepository;

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;

        // Bearer Token(JWT)
        String authorization = request.getHeader("Authorization");  // 다운 캐스팅

        if (jwtProvider.validateToken(authorization)) {
            setJwtAuthentication(authorization);
        }

        filterChain.doFilter(servletRequest, servletResponse);
    }

    private void setJwtAuthentication(String bearerToken) {

        Claims claims = jwtProvider.parseToken(bearerToken);    // 토큰 검증

        if(claims == null) {

            throw new JwtException("Invalid JWT token");
        }

        int userId = Integer.parseInt(claims.get("userId").toString()); // Claims 을 int 타입으로 변환

        userRepository.findById(userId).ifPresent(user -> {
            SecurityContext securityContext = SecurityContextHolder.getContext();
            PrincipalUser principalUser = new PrincipalUser(user);

            //  Authentication 객체 생성
            Authentication authentication = new UsernamePasswordAuthenticationToken(principalUser, principalUser.getPassword(), principalUser.getAuthorities());
            securityContext.setAuthentication(authentication);
        });
    }

}
