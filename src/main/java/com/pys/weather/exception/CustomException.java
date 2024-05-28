package com.pys.weather.exception;

import com.pys.weather.model.ErrorEnum;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class CustomException extends RuntimeException{
    private final ErrorEnum errorEnum;
}
