package com.zerobase.weather.controller;

import com.zerobase.weather.model.JwtToken;
import com.zerobase.weather.model.request.SignInRequest;
import com.zerobase.weather.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/users")
public class UserController {
    private final UserService userService;

    @PostMapping("/sign-in")
    public JwtToken signIn(@RequestBody SignInRequest request) {
        log.info("=================[START] SignIn==================");
        log.info("request: {}", request);

        JwtToken jwtToken = userService.signIn(request);

        log.info("jwtToken: {}", jwtToken);
        log.info("=================[END] signIn================");

        return jwtToken;
    }
}
