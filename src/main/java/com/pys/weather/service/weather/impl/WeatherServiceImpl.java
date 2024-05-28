package com.pys.weather.service.weather.impl;

import com.pys.weather.entity.Weather;
import com.pys.weather.feign.WeatherClient;
import com.pys.weather.model.LocationEnum;
import com.pys.weather.model.WeatherInfo;
import com.pys.weather.model.WeatherMain;
import com.pys.weather.model.response.weather.WeatherConvertResponse;
import com.pys.weather.repository.WeatherRepository;
import com.pys.weather.model.request.weather.CreateWeatherCityRequest;
import com.pys.weather.model.response.feign.WeatherInfoResponse;
import com.pys.weather.service.weather.WeatherService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class WeatherServiceImpl implements WeatherService {
    @Value("${weather.key}")
    private String key;
    private final WeatherClient weatherClient;
    private final WeatherRepository weatherRepository;
    @Override
    @Transactional
    public List<WeatherInfoResponse> getWeatherInfo() {
        List<WeatherInfoResponse> result = new ArrayList<>();

        for(LocationEnum lc : LocationEnum.values()) {
            WeatherInfoResponse response = processWeatherInfo(lc);
            if(!ObjectUtils.isEmpty(response)) {
                result.add(response);
            }
        }
        return result;
    }

    @Override
    public void createWeatherByCity(CreateWeatherCityRequest request) {
        BigDecimal celsius = convertFahrenToCels(request.getTemp());
        weatherRepository.save(Weather.builder()
                        .locId(request.getLocId())
                        .temp(celsius)
                        .wthCond(request.getWthCond())
                        .wthCondDesc(request.getWthCondDesc())
                        .wthIconUrl(request.getWthIconUrl())
                        .wthDate(request.getWthDate())
                        .build());
    }
    @Transactional
    @Scheduled(cron = "0 0 7 * * ?")
//    @Scheduled(fixedRate = 10000)
    public void weatherSchedule() {
       for(LocationEnum lc : LocationEnum.values()) {
           processWeatherInfo(lc);
       }

    }

    private String unixTimeToString(Long unixTime) {
        Instant instant = Instant.ofEpochSecond(unixTime);
        // 시간대 설정 (예: 서울)
        ZoneId zoneId = ZoneId.of("Asia/Seoul");
        ZonedDateTime dateTime = instant.atZone(zoneId);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
        return  dateTime.format(formatter);
    }

    private BigDecimal convertFahrenToCels(BigDecimal temp) {
        return temp.subtract(BigDecimal.valueOf(273.15));
    }

    private WeatherInfoResponse processWeatherInfo(LocationEnum locationEnum) {
        String cityId = locationEnum.getLocCd();
        WeatherConvertResponse dto = weatherClient.getWeatherInfo(cityId, key);

        log.info("response : {}", dto);

        WeatherMain main = dto.getMain();
        WeatherInfo weather = dto.getWeather().get(0);
        Integer locId = LocationEnum.getLocIdByLocCd(dto.getLocCd());

        createWeatherByCity(CreateWeatherCityRequest.builder()
                .locId(locId)
                .temp(main.getTemp())
                .wthCond(weather.getMain())
                .wthCondDesc(weather.getDescription())
                .wthIconUrl(weather.getIcon())
                .wthDate(unixTimeToString(dto.getUnixDt()))
                .build());

        return WeatherInfoResponse.builder()
                .icon(weather.getIcon())
                .weatherCondition(weather.getMain())
                .weatherCondDescr(weather.getDescription())
                .temp(convertFahrenToCels(main.getTemp()))
                .locCd(dto.getLocCd())
                .locNm(dto.getLocNm())
                .unixDt(dto.getUnixDt())
                .build();
    }
}
