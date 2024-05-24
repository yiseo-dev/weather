package com.zerobase.weather.controller;

import com.zerobase.weather.config.Response;
import com.zerobase.weather.model.JwtToken;
import com.zerobase.weather.model.request.user.SignInRequest;
import com.zerobase.weather.model.request.user.SignUpRequest;
import com.zerobase.weather.model.response.user.UserInfoResponse;
import com.zerobase.weather.service.user.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    @GetMapping("/user-info")
    public ResponseEntity<Response> findUserInfo() {
        log.info("=================[START] findUserInfo==================");

        UserInfoResponse response = userService.findUserInfo();

        log.info("response: {}", response);
        log.info("=================[END] signIn================");
        return ResponseEntity.ok(Response.builder()
                .data(response)
                .build());
    }
    @PostMapping("/sign-up")
    public ResponseEntity<HttpStatus> regUser(@RequestBody SignUpRequest request) {
        log.info("=================[START] regUser==================");
        log.info("request: {}", request);

        userService.regUser(request);

        log.info("=================[END] regUser================");
        return ResponseEntity.ok(HttpStatus.CREATED);
    }
}
