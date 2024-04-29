package com.zerobase.weather.model;

import com.zerobase.weather.entity.Diary;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

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
    private String emotionCd;
    private Integer sleep;
    private String imgUrl;
    private Long locId;
    private BigDecimal temp;
    private String wthCond;
    private String wthCondDesc;
    private String wthIconUrl;
    private String wthDate;

    public static List<DiaryInfo> toDto(List<Diary> diary) {
        List<DiaryInfo> result = new ArrayList<>();
        for(Diary entity : diary) {
            result.add(DiaryInfo.builder()
                            .diaryId(entity.getDiaryId())
                            .userId(entity.getUserId())
                            .diaryDate(entity.getDiaryDate())
                            .diaryContent(entity.getDiaryContent())
                            .activityCd(entity.getActivityCd())
                            .weatherId(entity.getWeather().getWeatherId())
                            .emotionCd(entity.getEmotionCd())
                            .sleep(entity.getSleep())
                            .imgUrl(entity.getImgUrl())
                            .locId(entity.getWeather().getLocId())
                            .temp(entity.getWeather().getTemp())
                            .wthCond(entity.getWeather().getWthCond())
                            .wthCondDesc(entity.getWeather().getWthCondDesc())
                            .wthDate(entity.getWeather().getWthDate())
                            .wthIconUrl(entity.getWeather().getWthIconUrl())
                            .build());
        }
        return result;
    }
}
