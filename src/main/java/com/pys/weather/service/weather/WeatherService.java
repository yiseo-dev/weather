package com.pys.weather.service.weather;

import com.pys.weather.model.request.weather.CreateWeatherCityRequest;
import com.pys.weather.model.response.feign.WeatherInfoResponse;

import java.util.List;

public interface WeatherService {
    List<WeatherInfoResponse> getWeatherInfo();

    void createWeatherByCity(CreateWeatherCityRequest request);
}
