package com.zerobase.weather.service.impl;

import com.zerobase.weather.model.JwtToken;
import com.zerobase.weather.service.UserService;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.transaction.annotation.Transactional;

public class UserServiceImpl implements UserService {
    @Override
    @Transactional
    public JwtToken signIn(String username, String password) {
        // 1. username + password를 기반으로 Authentication 객체 생성
        // 이때 authentication은 인증 여부를 확인하는 authenticated 값이 false
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(username, password);

        // 2. 실제 검증 .authenticate() 메서드를 통해 요청된 User에 대한 검증 진행
        // authenticate 메서드가 실행될 때 CustomUserDetailsService에서 만든 loadUserByUsername 메서드 실행
        return null;
    }
}
