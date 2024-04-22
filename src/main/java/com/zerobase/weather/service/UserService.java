package com.zerobase.weather.service;

import com.zerobase.weather.model.JwtToken;
import com.zerobase.weather.model.request.SignInRequest;
import com.zerobase.weather.model.request.SignUpRequest;
import com.zerobase.weather.model.response.user.UserInfoResponse;

public interface UserService {
    JwtToken signIn(SignInRequest request);
    UserInfoResponse findUserInfo();

    void regUser(SignUpRequest request);
}
