package com.zerobase.weather.model.response.weather;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.zerobase.weather.model.WeatherInfo;
import com.zerobase.weather.model.WeatherMain;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
public class WeatherConvertResponse {
    @JsonProperty("weather")
    private List<WeatherInfo> weather;

    @JsonProperty("main")
    private WeatherMain main;

    @JsonProperty("id")
    private String locCd;

    @JsonProperty("name")
    private String locNm;

    @JsonProperty("dt")
    private Long unixDt;
}