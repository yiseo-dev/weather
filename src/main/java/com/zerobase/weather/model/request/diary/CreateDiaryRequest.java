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
public class CreateDiaryRequest {
    @Schema(description = "회원Id", example = "1")
    private Long userId;
    @Schema(description = "일기 작성 일자", example = "20240528")
    private String diaryDate;
    @Schema(description = "일기 내용")
    private String diaryContent;
    @Schema(description = "활동 코드 (climbing)", example = "1")
    private String activityCd;
    @Schema(description = "감정 코드 (happy)", example = "1")
    private String emotionCd;
    @Schema(description = "수면 시간", example = "8")
    private Integer sleep;
    @Schema(description = "이미지 url")
    private String imgUrl;
}
