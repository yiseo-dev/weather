package model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
@AllArgsConstructor
public class JwtToken {
    // Bearer 방식을 사용할 예정
    private String grantType;
    private String accessToken;
    private String refreshToken;
}
