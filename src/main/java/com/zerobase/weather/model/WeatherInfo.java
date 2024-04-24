package com.zerobase.weather.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class WeatherInfo {
    String weatherCondition;
    String weatherCondDescr;
    String icon;
}