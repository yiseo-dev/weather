package com.zerobase.weather.service.user;

import com.zerobase.weather.model.JwtToken;
import com.zerobase.weather.model.request.user.SignInRequest;
import com.zerobase.weather.model.request.user.SignUpRequest;
import com.zerobase.weather.model.response.user.UserInfoResponse;

public interface UserService {
    JwtToken signIn(SignInRequest request);
    UserInfoResponse findUserInfo();

    void regUser(SignUpRequest request);
}
