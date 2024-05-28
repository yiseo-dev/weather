package com.pys.weather.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "weather")
public class Weather {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "WEATHER_ID")
    private Long weatherId;

    @Column(name = "LOC_ID")
    private Integer locId;

    @Column(name = "TEMP")
    private BigDecimal temp;

    @Column(name = "WTH_COND")
    private String wthCond;

    @Column(name = "WTH_COND_DESC")
    private String wthCondDesc;

    @Column(name = "WTH_ICON_URL")
    private String wthIconUrl;

    @Column(name = "WTH_DATE")
    private String wthDate;
}
