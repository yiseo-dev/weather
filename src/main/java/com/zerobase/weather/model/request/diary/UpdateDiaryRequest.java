package com.zerobase.weather.model.request.diary;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UpdateDiaryRequest {
    private Long diaryId;
    private String diaryContent;
    private Integer sleep;
    private String emotionCd;
    private String imgUrl;
    private String activityCd;
}
