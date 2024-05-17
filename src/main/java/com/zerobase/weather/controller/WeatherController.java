package com.zerobase.weather.controller;

import com.zerobase.weather.config.Response;
import com.zerobase.weather.model.request.weather.CreateWeatherCityRequest;
import com.zerobase.weather.model.response.feign.WeatherInfoResponse;
import com.zerobase.weather.service.WeatherService;
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
