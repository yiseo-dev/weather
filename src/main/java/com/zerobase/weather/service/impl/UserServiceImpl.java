package com.zerobase.weather.service.impl;

import com.zerobase.weather.entity.Users;
import com.zerobase.weather.exception.CustomException;
import com.zerobase.weather.jwt.JwtTokenProvider;
import com.zerobase.weather.model.JwtToken;
import com.zerobase.weather.model.RoleEnum;
import com.zerobase.weather.model.request.SignInRequest;
import com.zerobase.weather.model.request.SignUpRequest;
import com.zerobase.weather.model.response.user.UserInfoResponse;
import com.zerobase.weather.repository.UserRepository;
import com.zerobase.weather.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static com.zerobase.weather.model.ErrorEnum.AUTHENTICATION_INFORMATION_NOT_FOUND;
import static com.zerobase.weather.model.ErrorEnum.CAN_NOT_USE_NAME;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final AuthenticationManagerBuilder authenticationManagerBuilder;
    private final JwtTokenProvider jwtTokenProvider;
    private final PasswordEncoder passwordEncoder;

    @Override
    @Transactional
    public JwtToken signIn(SignInRequest request) {
        // 1. username + password를 기반으로 Authentication 객체 생성
        // 이때 authentication은 인증 여부를 확인하는 authenticated 값이 false
        String userNm = request.getUserNm();
        String userPw = request.getUserPw();
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(userNm, userPw);

        // 2. 실제 검증 .authenticate() 메서드를 통해 요청된 User에 대한 검증 진행
        // authenticate 메서드가 실행될 때 UserDetailsService에서 만든 loadUserByUsername 메서드 실행
        Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);

        // 3. 인증 정보를 기반으로 JWT 토큰 생성
        JwtToken jwtToken = jwtTokenProvider.generateToken(authentication);
        return jwtToken;
    }

    @Override
    public  UserInfoResponse findUserInfo() {
        final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || authentication.getName() == null) {
            throw new CustomException(AUTHENTICATION_INFORMATION_NOT_FOUND);
        }
        log.info("권한 정보 상세: {}",authentication.getDetails());
        return UserInfoResponse.builder().userNm(authentication.getName()).build();
    }

    @Override
    @Transactional
    public void regUser(SignUpRequest request) {
        Optional<Users> users = userRepository.findByUserNm(request.getUserNm());
        if (users.isPresent()) {
            throw new CustomException(CAN_NOT_USE_NAME);
        }
        // Password 암호화
        String encodedPassword = passwordEncoder.encode(request.getUserPw());
        userRepository.save(request.toEntity(encodedPassword, RoleEnum.USER));
    }
}
