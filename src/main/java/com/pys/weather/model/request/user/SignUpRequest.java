package com.pys.weather.model.request.user;

import com.pys.weather.entity.Users;
import com.pys.weather.model.RoleEnum;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SignUpRequest {
    @Schema(description = "회원Id", example = "1")
    private Long userId;
    @Schema(description = "회원 이름", example = "yi")
    private String userNm;
    @Schema(description = "회원 비밀 번호", example = "1234")
    private String userPw;
    @Schema(description = "회원 이메일", example = "user123@naver.com")
    private String userEmail;
    @Schema(description = "제공자Id")
    private String provider;
    @Schema(description = "제공자에서 저장하는 회원Id")
    private String providerUserId;
    @Schema(description = "위치Id")
    private Integer locId;
    @Schema(description = "역할", example = "USER/ADMIN")
    private RoleEnum role; // Enum으로 정의된 역할

    public Users toEntity(String encodedPw, RoleEnum role) {
        return Users.builder()
                .userNm(userNm)
                .locId(locId)
                .provider(provider)
                .providerUserId(providerUserId)
                .role(role)
                .userEmail(userEmail)
                .userPw(encodedPw)
                .build();
    }
}
