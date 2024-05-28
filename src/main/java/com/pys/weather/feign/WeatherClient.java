package com.pys.weather.feign;

import com.pys.weather.model.response.weather.WeatherConvertResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "weather", url = "https://api.openweathermap.org/data/2.5")
public interface WeatherClient {

    @GetMapping("/weather")
    public WeatherConvertResponse getWeatherInfo(@RequestParam("id") String id, @RequestParam("appid") String appid);
}
