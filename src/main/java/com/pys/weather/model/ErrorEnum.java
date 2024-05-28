package com.pys.weather.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.http2.HpackDecoder;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum ErrorEnum {
    TOKEN_PERMISSION_ERROR(HttpStatus.NON_AUTHORITATIVE_INFORMATION, "권한 정보가 없는 토큰입니다."),
    USER_NOT_FOUND(HttpStatus.NOT_FOUND, "해당 회원을 찾을 수 없습니다."),
    AUTHENTICATION_INFORMATION_NOT_FOUND(HttpStatus.NON_AUTHORITATIVE_INFORMATION, "권한 정보를 찾을 수 없습니다."),
    CAN_NOT_USE_NAME(HttpStatus.NOT_ACCEPTABLE, "이미 사용 중인 사용자 이름입니다."),
    CAN_NOT_FOUND_DIARY(HttpStatus.NOT_FOUND, "해당 일기 데이터를 찾을 수 없습니다."),
    CAN_NOT_FOUND_WEATHER(HttpStatus.NOT_FOUND, "해당 날씨 데이터를 찾을 수 없습니다."),
    CAN_NOT_UPDATE_DIARY(HttpStatus.EXPECTATION_FAILED, "일기 수정에 실패했습니다."),
    USER_DOES_NOT_MATCH(HttpStatus.NOT_ACCEPTABLE, "로그인한 회원과 일치하지 않는 회원입니다."),
    ;

    private final HttpStatus httpStatus;
    private final String errMsg;
}
