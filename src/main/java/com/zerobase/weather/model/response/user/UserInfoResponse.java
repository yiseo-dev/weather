package com.zerobase.weather.model.response.user;

import com.zerobase.weather.model.RoleEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserInfoResponse {
    private Long userId;
    private String userNm;
    private RoleEnum role;
    private String userEmail;
    private String provider;
    private String providerUserId;
    private Long locId;
}
