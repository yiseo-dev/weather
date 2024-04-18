package com.zerobase.weather.service;

import com.zerobase.weather.model.JwtToken;

public interface UserService {
    JwtToken signIn(String username, String password);
}
