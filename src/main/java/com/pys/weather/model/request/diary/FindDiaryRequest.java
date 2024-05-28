package com.pys.weather.model.request.diary;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class FindDiaryRequest {
    @Schema(description = "회원Id", example = "1")
    private Long userId;
    @Schema(description = "일기 작성 월", example = "202405")
    private String yearMonth;
}
