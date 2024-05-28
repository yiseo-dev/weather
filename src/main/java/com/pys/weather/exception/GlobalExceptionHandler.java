package com.pys.weather.exception;

import com.pys.weather.model.response.ErrorResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(CustomException.class)
    public ResponseEntity<ErrorResponse> customException(CustomException ce) {
        return ResponseEntity
                .status(ce.getErrorEnum().getHttpStatus())
                .body(new ErrorResponse(ce.getErrorEnum().getHttpStatus(), ce.getErrorEnum().getErrMsg()));
    }

}
