package com.zerobase.weather.controller;

import com.zerobase.weather.config.Response;
import com.zerobase.weather.model.request.weather.CreateWeatherCityRequest;
import com.zerobase.weather.model.response.feign.WeatherInfoResponse;
import com.zerobase.weather.service.weather.WeatherService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/weather")
public class WeatherController {
    private final WeatherService weatherService;

    @GetMapping("/cities")
    @Operation(summary = "날씨 정보 조회", description = "날씨 정보 조회하는 API", tags = {"WeatherController"})
    public ResponseEntity<Response> getWeatherInfo() {
        log.info("=================[START] getWeatherInfo==================");

        List<WeatherInfoResponse> response = weatherService.getWeatherInfo();

        log.info("response: {}", response);
        log.info("=================[END] getWeatherInfo==================");

        return ResponseEntity.ok(Response.builder()
                .data(response)
                .build());
    }
    @PostMapping("/city")
    @Operation(summary = "날씨 정보 저장", description = "날씨 정보 맵핑하여 저장하는 API", tags = {"WeatherController"})
    public ResponseEntity<Response> createWeatherByCity(@RequestBody CreateWeatherCityRequest request){
        log.info("=================[START] createWeatherByCity==================");
        log.info("request: {}", request);

        weatherService.createWeatherByCity(request);

        log.info("=================[END] createWeatherByCity==================");
        return ResponseEntity.ok(Response.builder()
                        .data(HttpStatus.CREATED)
                        .build());
    }
}
