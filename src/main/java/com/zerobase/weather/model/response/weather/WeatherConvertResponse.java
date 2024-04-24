package com.zerobase.weather.model.response.weather;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
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
    private List<Object> weather;

    @JsonProperty("main")
    private Object main;

    @JsonProperty("id")
    private String locCd;

    @JsonProperty("name")
    private String locNm;

    @JsonProperty("dt")
    private Long unixDt;
}