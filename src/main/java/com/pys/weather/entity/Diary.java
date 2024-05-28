package com.pys.weather.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "diary")
public class Diary {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "DIARY_ID")
    private Long diaryId;

    @Column(name = "USER_ID")
    private long userId;

    @Column(name = "DIARY_DATE")
    private String diaryDate;

    @Column(name = "DIARY_CONTENT")
    private String diaryContent;

    @ManyToOne
    @JoinColumn(name = "WEATHER_ID")
    private Weather weather;

    @Column(name = "ACTIVITY_CD")
    private String activityCd;

    @Column(name = "EMOTION_CD")
    private String emotionCd;

    @Column(name = "sleep")
    private Integer sleep;

    @Column(name = "IMG_URL")
    private String imgUrl;
}
