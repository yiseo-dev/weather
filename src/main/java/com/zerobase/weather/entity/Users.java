package com.zerobase.weather.entity;

import com.zerobase.weather.model.RoleEnum;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity(name = "user")
public class Users implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "USER_ID")
    private Long userId;
    @Column(name = "USER_NM")
    private String userNm;
    @Column(name = "USER_PW")
    private String userPw;
    @Column(name = "USER_EMAIL")
    private String userEmail;
    @Column(name = "PROVIDER")
    private String provider;
    @Column(name = "PROVIDER_USER_ID")
    private String providerUserId;
    @Column(name = "LOC_ID")
    private Integer locId;
    @Enumerated(EnumType.STRING)
    private RoleEnum role; // Enum으로 정의된 역할

    public Users(String userNm) {
        this.userNm = userNm;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority("ROLE_" + role.name()));
        return authorities;
    }

    @Override
    public String getPassword() {
        return this.userPw;
    }

    @Override
    public String getUsername() {
        return this.userNm;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return false;
    }
}
