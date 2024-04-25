package com.zerobase.weather.service.impl;

import com.zerobase.weather.entity.Diary;
import com.zerobase.weather.model.request.diary.CreateDiaryRequest;
import com.zerobase.weather.repository.DiaryRepository;
import com.zerobase.weather.service.DiaryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DiaryServiceImpl implements DiaryService {
    private final DiaryRepository diaryRepository;
    @Override
    public void createDiary(CreateDiaryRequest request) {
        diaryRepository.save(Diary.builder()
                        .diaryContent(request.getDiaryContent())
                        .diaryDate(request.getDiaryDate())
                        .sleep(request.getSleep())
                        .activityCd(request.getActivityCd())
                        .imgUrl(request.getImgUrl())
                        .userId(request.getUserId())
                        .weatherId(request.getWeatherId())
                        .emotionCd(request.getEmotionCd())
                        .build());
    }
}
