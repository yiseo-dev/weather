package com.pys.weather.model;

import com.pys.weather.entity.Diary;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DiaryInfo {
    private Long diaryId;
    private Long userId;
    private String  diaryDate;
    private String diaryContent;
    private Long weatherId;
    private String activityCd;
    private String activityNm;
    private String emotionCd;
    private String emotionNm;
    private Integer sleep;
    private String imgUrl;
    private Integer locId;
    private BigDecimal temp;
    private String wthCond;
    private String wthCondDesc;
    private String wthIconUrl;
    private String wthDate;

    public static DiaryInfo toDto(Diary diary) {
        return DiaryInfo.builder()
                .diaryId(diary.getDiaryId())
                .userId(diary.getUserId())
                .diaryDate(diary.getDiaryDate())
                .diaryContent(diary.getDiaryContent())
                .activityCd(diary.getActivityCd())
                .weatherId(diary.getWeather().getWeatherId())
                .emotionCd(diary.getEmotionCd())
                .sleep(diary.getSleep())
                .imgUrl(diary.getImgUrl())
                .locId(diary.getWeather().getLocId())
                .temp(diary.getWeather().getTemp())
                .wthCond(diary.getWeather().getWthCond())
                .wthCondDesc(diary.getWeather().getWthCondDesc())
                .wthDate(diary.getWeather().getWthDate())
                .wthIconUrl(diary.getWeather().getWthIconUrl())
                .build();
    }
}
