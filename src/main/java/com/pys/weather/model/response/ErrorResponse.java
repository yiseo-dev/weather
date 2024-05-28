package com.pys.weather.model.response;

import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
public class ErrorResponse {
    private HttpStatus httpStatus;
    private String errMsg;

    public ErrorResponse(HttpStatus httpStatus, String errMsg) {
        this.httpStatus = httpStatus;
        this.errMsg = errMsg;
    }
}
