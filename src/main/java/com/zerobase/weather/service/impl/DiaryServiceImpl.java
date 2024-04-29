package com.zerobase.weather.service.impl;

import com.zerobase.weather.entity.Diary;
import com.zerobase.weather.entity.Weather;
import com.zerobase.weather.exception.CustomException;
import com.zerobase.weather.model.DiaryInfo;
import com.zerobase.weather.model.request.diary.CreateDiaryRequest;
import com.zerobase.weather.model.request.diary.FindDiaryRequest;
import com.zerobase.weather.model.request.diary.UpdateDiaryRequest;
import com.zerobase.weather.model.response.diary.DiaryInfoResponse;
import com.zerobase.weather.repository.DiaryRepository;
import com.zerobase.weather.repository.WeatherRepository;
import com.zerobase.weather.service.DiaryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;

import static com.zerobase.weather.model.ErrorEnum.CAN_NOT_FOUND_DIARY;
import static com.zerobase.weather.model.ErrorEnum.CAN_NOT_FOUND_WEATHER;

@Slf4j
@Service
@RequiredArgsConstructor
public class DiaryServiceImpl implements DiaryService {
    private final DiaryRepository diaryRepository;
    private final WeatherRepository weatherRepository;
    @Override
    public void createDiary(CreateDiaryRequest request) {

        Weather weather = weatherRepository.findById(request.getWeatherId()).orElseThrow(() -> new CustomException(CAN_NOT_FOUND_WEATHER));

        diaryRepository.save(Diary.builder()
                        .diaryContent(request.getDiaryContent())
                        .diaryDate(request.getDiaryDate())
                        .sleep(request.getSleep())
                        .activityCd(request.getActivityCd())
                        .imgUrl(request.getImgUrl())
                        .userId(request.getUserId())
                        .weather(weather)
                        .emotionCd(request.getEmotionCd())
                        .build());
    }

    @Override
    public DiaryInfoResponse findDiaryByUser(FindDiaryRequest request) {
        int sum = 0;
        String startDate = request.getYearMonth().concat("01");
        String endDate = request.getYearMonth().concat("31");

        List<Diary> diaryInfo = diaryRepository.findAllByUserIdAndDiaryDateBetween(request.getUserId(), startDate, endDate);
        log.info("diaryInfo : {}", diaryInfo);

        List<DiaryInfo> diaryInfoList = DiaryInfo.toDto(diaryInfo);

        for(DiaryInfo infos : diaryInfoList) {
            if(infos.getSleep() != null){
                sum += infos.getSleep();
            }
        }

        int sleepAvg = 0;
        if(diaryInfoList.size()>0){
            sleepAvg = sum / diaryInfoList.size();
        }
        return DiaryInfoResponse.builder()
                .diaryInfoList(diaryInfoList)
                .sleepAvg(sleepAvg)
                .build();
    }

    @Override
    public DiaryInfo findDiaryById(Long diaryId) {

        Optional<Diary> diaryInfo = diaryRepository.findById(diaryId);

        return diaryInfo.map(diary -> DiaryInfo.builder()
                .diaryId(diary.getDiaryId())
                .diaryContent(diary.getDiaryContent())
                .diaryDate(diary.getDiaryDate())
                .sleep(diary.getSleep())
                .userId(diary.getUserId())
                .weatherId(diary.getWeather().getWeatherId())
                .imgUrl(diary.getImgUrl())
                .emotionCd(diary.getEmotionCd())
                .activityCd(diary.getActivityCd())
                .locId(diary.getWeather().getLocId())
                .temp(diary.getWeather().getTemp())
                .wthCond(diary.getWeather().getWthCond())
                .wthCondDesc(diary.getWeather().getWthCondDesc())
                .wthDate(diary.getWeather().getWthDate())
                .wthIconUrl(diary.getWeather().getWthIconUrl())
                .build()).orElseThrow(() -> new CustomException(CAN_NOT_FOUND_DIARY));
    }

    @Override
    @Transactional
    public void updateDiaryById(UpdateDiaryRequest request) {
        Diary diary = diaryRepository.findById(request.getDiaryId()).orElseThrow(() -> new CustomException(CAN_NOT_FOUND_DIARY));
        BeanWrapperImpl wrapper = new BeanWrapperImpl(request);
        String[] properties = {"diaryContent", "sleep", "activityCd", "emotionCd", "imgUrl"};

        for(String propertyName : properties) {
            Object value = wrapper.getPropertyValue(propertyName);
            if(value!=null) {
                setProperty(diary,propertyName,value);
            }
        }
    }

    @Override
    @Transactional
    public void deletDiaryById(Long diaryId) {
        diaryRepository.deleteById(diaryId);
    }

    private void setProperty(Diary diary, String propertyName, Object value){
        BeanWrapperImpl wrapper = new BeanWrapperImpl(diary);
        wrapper.setPropertyValue(propertyName,value);
    }
}
