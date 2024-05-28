package com.pys.weather.model.request.weather;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CreateWeatherCityRequest {
    @Schema(description = "위치Id", example = "1(서울)")
    private Integer locId;
    @Schema(description = "온도(섭씨)", example = "26")
    private BigDecimal temp;
    @Schema(description = "날씨 상태", example = "Clear")
    private String wthCond;
    @Schema(description = "날씨 상태 설명", example = "clear sky")
    private String wthCondDesc;
    @Schema(description = "날씨 이모지", example = "01d")
    private String wthIconUrl;
    @Schema(description = "날씨 날짜", example = "20240528")
    private String wthDate;
}
