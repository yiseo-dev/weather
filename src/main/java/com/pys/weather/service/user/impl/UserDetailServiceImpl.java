package com.pys.weather.service.user.impl;

import com.pys.weather.entity.Users;
import com.pys.weather.exception.CustomException;
import com.pys.weather.model.ErrorEnum;
import com.pys.weather.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class UserDetailServiceImpl implements UserDetailsService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;


    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        return userRepository.findByUserNm(userName)
                .map(this::createUserDetails)
                .orElseThrow(() -> new CustomException(ErrorEnum.USER_NOT_FOUND));
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
