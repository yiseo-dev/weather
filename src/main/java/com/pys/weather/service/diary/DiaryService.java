package com.pys.weather.service.diary;

import com.pys.weather.model.DiaryInfo;
import com.pys.weather.model.request.diary.CreateDiaryRequest;
import com.pys.weather.model.response.diary.DiaryInfoResponse;
import com.pys.weather.model.request.diary.FindDiaryRequest;
import com.pys.weather.model.request.diary.UpdateDiaryRequest;

public interface DiaryService {
    void createDiary(CreateDiaryRequest request);

    DiaryInfoResponse findDiaryByUser(FindDiaryRequest request);

    DiaryInfo findDiaryById(Long diaryId);

    void updateDiaryById(UpdateDiaryRequest request);

    void deletDiaryById(Long diaryId);
}
