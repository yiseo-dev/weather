package com.zerobase.weather.service.impl;

import com.zerobase.weather.feign.WeatherClient;
import com.zerobase.weather.model.WeatherInfo;
import com.zerobase.weather.model.WeatherMain;
import com.zerobase.weather.model.response.feign.WeatherInfoResponse;
import com.zerobase.weather.model.response.weather.WeatherConvertResponse;
import com.zerobase.weather.service.WeatherService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class WeatherServiceImpl implements WeatherService {
    @Value("${weather.key}")
    private String key;
    private final WeatherClient weatherClient;
    @Override
    public WeatherInfoResponse getWeatherInfo(String cityId) {
        WeatherConvertResponse dto = weatherClient.getWeatherInfo(cityId,key);

        log.info("response : {}", dto);

        WeatherMain main = dto.getMain();
        WeatherInfo weather = dto.getWeather().get(0);

        return WeatherInfoResponse.builder()
                .icon(weather.getIcon())
                .weatherCondition(weather.getMain())
                .weatherCondDescr(weather.getDescription())
                .temp(main.getTemp())
                .locCd(dto.getLocCd())
                .locNm(dto.getLocNm())
                .unixDt(dto.getUnixDt())
                .build();
    }
}
