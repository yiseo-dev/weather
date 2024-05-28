package com.pys.weather.controller;

import com.pys.weather.config.Response;
import com.pys.weather.model.response.user.UserInfoResponse;
import com.pys.weather.model.JwtToken;
import com.pys.weather.model.request.user.SignInRequest;
import com.pys.weather.model.request.user.SignUpRequest;
import com.pys.weather.service.user.UserService;
import io.swagger.v3.oas.annotations.Operation;
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
    @Operation(summary = "로그인", description = "토큰 발급 받는 API", tags = {"UserController"})
    public JwtToken signIn(@RequestBody SignInRequest request) {
        log.info("=================[START] SignIn==================");
        log.info("request: {}", request);

        JwtToken jwtToken = userService.signIn(request);

        log.info("jwtToken: {}", jwtToken);
        log.info("=================[END] signIn================");

        return jwtToken;
    }
    @GetMapping("/user-info")
    @Operation(summary = "회원 정보 조회", description = "토큰 통한 회원 정보 조회", tags = {"UserController"})
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
    @Operation(summary = "회원 가입", description = "회원 가입 API", tags = {"UserController"})
    public ResponseEntity<HttpStatus> regUser(@RequestBody SignUpRequest request) {
        log.info("=================[START] regUser==================");
        log.info("request: {}", request);

        userService.regUser(request);

        log.info("=================[END] regUser================");
        return ResponseEntity.ok(HttpStatus.CREATED);
    }
}
