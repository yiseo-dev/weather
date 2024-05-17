package com.zerobase.weather.service;

import com.zerobase.weather.model.request.weather.CreateWeatherCityRequest;
import com.zerobase.weather.model.response.feign.WeatherInfoResponse;
import com.zerobase.weather.model.response.weather.WeatherConvertResponse;

import java.util.List;

public interface WeatherService {
    List<WeatherInfoResponse> getWeatherInfo();

    void createWeatherByCity(CreateWeatherCityRequest request);
}
