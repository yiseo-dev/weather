package com.zerobase.weather.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "weather")
public class Weather {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "WEATHER_ID")
    private Long weatherId;
    @Column(name = "LOC_ID")
    private long locId;
    @Column(name = "TEMP_MIN")
    private long tempMin;
    @Column(name = "TEMP_MAX")
    private long tempMax;
    @Column(name = "WTH_COND_CD")
    private String wthCondCd;
    @Column(name = "WTH_COND_NM")
    private String wthCondNm;
    @Column(name = "WTH_DATE")
    private String wthDate;
}
