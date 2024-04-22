package com.zerobase.weather.service.impl;

import com.zerobase.weather.entity.Users;
import com.zerobase.weather.exception.CustomException;
import com.zerobase.weather.model.ErrorEnum;
import com.zerobase.weather.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import static com.zerobase.weather.model.ErrorEnum.USER_NOT_FOUND;


@Service
@RequiredArgsConstructor
public class UserDetailServiceImpl implements UserDetailsService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;


    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        return userRepository.findByUserNm(userName)
                .map(this::createUserDetails)
                .orElseThrow(() -> new CustomException(USER_NOT_FOUND));
    }

    private UserDetails createUserDetails(Users users) {
        return User.builder()
                .username(users.getUserNm())
                .password(users.getPassword())
                .authorities(users.getAuthorities().toArray(new GrantedAuthority[0]))
//                .accountExpired(!users.isAccountNonExpired())
//                .accountLocked(!users.isAccountNonLocked())
//                .credentialsExpired(!users.isCredentialsNonExpired())
//                .disabled(!users.isEnabled())
                .build();
    }
}
