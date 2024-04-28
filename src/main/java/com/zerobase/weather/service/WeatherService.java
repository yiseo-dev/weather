package com.zerobase.weather.service;

import com.zerobase.weather.model.request.weather.CreateWeatherCityRequest;
import com.zerobase.weather.model.response.feign.WeatherInfoResponse;
import com.zerobase.weather.model.response.weather.WeatherConvertResponse;

public interface WeatherService {
    WeatherInfoResponse getWeatherInfo(String cityId);

    void createWeatherByCity(CreateWeatherCityRequest request);
}
