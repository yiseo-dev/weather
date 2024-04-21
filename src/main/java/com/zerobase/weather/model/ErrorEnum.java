package com.zerobase.weather.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum ErrorEnum {
    TOKEN_PERMISSION_ERROR(HttpStatus.NON_AUTHORITATIVE_INFORMATION, "권한 정보가 없는 토큰입니다."),
    USER_NOT_FOUND(HttpStatus.NOT_FOUND, "해당 회원을 찾을 수 없습니다."),
    AUTHENTICATION_INFORMATION_NOT_FOUND(HttpStatus.NON_AUTHORITATIVE_INFORMATION, "권한 정보를 찾을 수 없습니다.")
    ;

    private final HttpStatus httpStatus;
    private final String errMsg;
}
