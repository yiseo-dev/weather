package com.zerobase.weather.model.request.diary;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class FindDiaryRequest {
    private Long userId;
    private String strDate;
    private String endDate;
}
