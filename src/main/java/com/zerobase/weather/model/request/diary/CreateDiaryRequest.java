package com.zerobase.weather.model.request.diary;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CreateDiaryRequest {
    private Long userId;
    private String diaryDate;
    private String diaryContent;
    private String activityCd;
    private String emotionCd;
    private Integer sleep;
    private String imgUrl;
}
