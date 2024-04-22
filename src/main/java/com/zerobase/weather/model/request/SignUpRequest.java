package com.zerobase.weather.model.request;

import com.zerobase.weather.entity.Users;
import com.zerobase.weather.model.RoleEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SignUpRequest {
    private Long userId;
    private String userNm;
    private String userPw;
    private String userEmail;
    private String provider;
    private String providerUserId;
    private long locId;
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
