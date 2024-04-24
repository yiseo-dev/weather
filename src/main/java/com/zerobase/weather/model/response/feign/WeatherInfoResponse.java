package com.zerobase.weather.model.response.feign;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
public class WeatherInfoResponse {
    private String weatherCondition;
    private String weatherCondDescr;
    private String icon;
    private Long temp;
    private String locCd;
    private String locNm;
    private Long unixDt;
}