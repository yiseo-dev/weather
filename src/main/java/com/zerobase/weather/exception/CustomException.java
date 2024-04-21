package com.zerobase.weather.exception;

import com.zerobase.weather.model.ErrorEnum;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class CustomException extends RuntimeException{
    private final ErrorEnum errorEnum;
}
