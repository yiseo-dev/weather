package com.zerobase.weather.model.request.diary;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UpdateDiaryRequest {
    @Schema(description = "일기Id", example = "1")
    private Long diaryId;
    @Schema(description = "일기 내용")
    private String diaryContent;
    @Schema(description = "수면 시간", example = "7")
    private Integer sleep;
    @Schema(description = "감정 코드(happy)", example = "1")
    private String emotionCd;
    @Schema(description = "이미지 url")
    private String imgUrl;
    @Schema(description = "활동 코드(climbing)", example = "1")
    private String activityCd;
}
