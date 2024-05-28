package com.pys.weather.model.response.diary;

import com.pys.weather.model.DiaryInfo;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DiaryInfoResponse {
    private List<DiaryInfo> diaryInfoList;
    private Integer sleepAvg;
}
