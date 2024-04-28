package com.zerobase.weather.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

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
}
