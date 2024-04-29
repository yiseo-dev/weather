package com.zerobase.weather.service;

import com.zerobase.weather.model.DiaryInfo;
import com.zerobase.weather.model.request.diary.CreateDiaryRequest;
import com.zerobase.weather.model.request.diary.FindDiaryRequest;
import com.zerobase.weather.model.request.diary.UpdateDiaryRequest;
import com.zerobase.weather.model.response.diary.DiaryInfoResponse;

public interface DiaryService {
    void createDiary(CreateDiaryRequest request);

    DiaryInfoResponse findDiaryByUser(FindDiaryRequest request);

    DiaryInfo findDiaryById(Long diaryId);

    void updateDiaryById(UpdateDiaryRequest request);

    void deletDiaryById(Long diaryId);
}
