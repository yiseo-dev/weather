package com.zerobase.weather.service;

import com.zerobase.weather.model.JwtToken;
import com.zerobase.weather.model.request.SignInRequest;

public interface UserService {
    JwtToken signIn(SignInRequest request);
}
