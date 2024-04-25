package com.zerobase.weather.service;

import com.zerobase.weather.model.request.diary.CreateDiaryRequest;

public interface DiaryService {
    void createDiary(CreateDiaryRequest request);
}
