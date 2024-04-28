package com.zerobase.weather.service.impl;

import com.zerobase.weather.entity.Diary;
import com.zerobase.weather.model.DiaryInfo;
import com.zerobase.weather.model.request.diary.CreateDiaryRequest;
import com.zerobase.weather.model.request.diary.FindDiaryRequest;
import com.zerobase.weather.model.response.diary.DiaryInfoResponse;
import com.zerobase.weather.repository.DiaryRepository;
import com.zerobase.weather.service.DiaryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

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

    @Override
    public DiaryInfoResponse findDiaryByUser(FindDiaryRequest request) {
//        String startDate = date;
//        String endDate = date;
        List<Diary> diaryInfo = diaryRepository.findAllByUserIdAndDiaryDateBetween(request.getUserId(), request.getStrDate(), request.getEndDate());
        return null;
    }
}
