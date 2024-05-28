package com.zerobase.weather.model.request.user;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SignInRequest {
    @Schema(description = "회원 이름", example = "yi")
    private String userNm;
    @Schema(description = "회원 비밀 번호", example = "1234")
    private String userPw;
}
