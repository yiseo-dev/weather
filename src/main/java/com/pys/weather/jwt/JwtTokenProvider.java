package com.pys.weather.jwt;

import com.pys.weather.entity.Users;
import com.pys.weather.exception.CustomException;
import com.pys.weather.model.ErrorEnum;
import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.SecurityException;
import lombok.extern.slf4j.Slf4j;
import com.pys.weather.model.JwtToken;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.security.Key;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.stream.Collectors;

@Slf4j
@Component
public class JwtTokenProvider {
    private final Key key;

    // application.yml에서 secret 값 가져와서 key에 저장
    public JwtTokenProvider(@Value("${jwt.secret}") String key) {
        byte[] keyBytes = Decoders.BASE64.decode(key);
        this.key = Keys.hmacShaKeyFor(keyBytes);
    }

    // User 정보를 가지고 AccessToken, RefreshToken 생성하는 메서드
    public JwtToken generateToken(Authentication authentication) {
        //권한 가져오기
        String authorities = authentication.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.joining(","));

        long now = (new Date()).getTime();

        // Access Token 생성(두시간)
        Date accessTokenExpiresln = new Date(now + 7200000);
        String accessToken = Jwts.builder()
                .subject(authentication.getName())
                .claim("auth",authorities)
                .expiration(accessTokenExpiresln)
                .signWith(key)
                .compact();

        // Refresh Token 생성(일주일)
        String refreshToken = Jwts.builder()
                .expiration(new Date(now + 604800000))
                .signWith(key)
                .compact();

        return JwtToken.builder()
                .grantType("Bearer")
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .build();
    }

    //Jwt 토큰을 복호화하여 토큰에 들어있는 정보를 꺼내는 메서드
    public Authentication getAuthentication(String accessToken) {
        // Jwt 토큰 복호화
        Claims claims = parseClaims(accessToken);
        if(claims.get("auth") == null) {
            throw new CustomException(ErrorEnum.TOKEN_PERMISSION_ERROR);
        }

        //클레임에서 권한 정보 가져오기
        Collection<?extends  GrantedAuthority> authorities =
                Arrays.stream(claims.get("auth").toString().split(","))
                        .map(SimpleGrantedAuthority::new)
                        .toList();

        //UserDetails 객체를 만들어서 Authentication return
        // UserDetails: interface, User: UserDetails를 구현한 class
        String userNm = claims.getSubject();
        UserDetails principal = new Users(userNm);
        return new UsernamePasswordAuthenticationToken(principal,"",authorities);
    }

    // 토큰 정보 검증
    public boolean validateToken(String token) {
        try{
         Jwts.parser()
                 .verifyWith((SecretKey) key)
                 .build()
                 .parseClaimsJws(token);
         return true;
        }catch(SecurityException | MalformedJwtException e) {
            log.info("Invalid JWT Token", e);
        }catch (ExpiredJwtException e) {
            log.info("Expired JWT Token", e);
        }catch(UnsupportedJwtException e) {
            log.info("Unsupported JWT Token",e);
        }catch(IllegalArgumentException e) {
            log.info("JWT claims string is empty", e);
        }
        return false;
    }

    private Claims parseClaims(String accessToken) {
        try {
            return Jwts.parser()
                    .verifyWith((SecretKey) key)
                    .build()
                    .parseSignedClaims(accessToken)
                    .getPayload();
        }catch (ExpiredJwtException e) {
            return e.getClaims();
        }
    }

}
