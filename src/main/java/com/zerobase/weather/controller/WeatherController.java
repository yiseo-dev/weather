package com.zerobase.weather.controller;

import com.zerobase.weather.config.Response;
import com.zerobase.weather.model.response.feign.WeatherInfoResponse;
import com.zerobase.weather.service.WeatherService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/weather")
public class WeatherController {
    private final WeatherService weatherService;

    @GetMapping("/open-api")
    public ResponseEntity<Response> getWeatherInfo(@RequestParam("id") String cityId) {

        return ResponseEntity.ok(Response.builder()
                .data(weatherService.getWeatherInfo(cityId))
                .build());
    }
}
