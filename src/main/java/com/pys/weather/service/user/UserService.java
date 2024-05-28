package com.pys.weather.service.user;

import com.pys.weather.model.request.user.SignInRequest;
import com.pys.weather.model.request.user.SignUpRequest;
import com.pys.weather.model.response.user.UserInfoResponse;
import com.pys.weather.model.JwtToken;

public interface UserService {
    JwtToken signIn(SignInRequest request);
    UserInfoResponse findUserInfo();

    void regUser(SignUpRequest request);
}
