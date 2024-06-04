package com.pys.weather.service.diary.impl;

import com.pys.weather.entity.Diary;
import com.pys.weather.entity.Weather;
import com.pys.weather.exception.CustomException;
import com.pys.weather.model.*;
import com.pys.weather.model.request.diary.CreateDiaryRequest;
import com.pys.weather.model.response.diary.DiaryInfoResponse;
import com.pys.weather.model.response.user.UserInfoResponse;
import com.pys.weather.repository.DiaryRepository;
import com.pys.weather.repository.WeatherRepository;
import com.pys.weather.service.diary.DiaryService;
import com.pys.weather.service.user.UserService;
import com.pys.weather.model.request.diary.FindDiaryRequest;
import com.pys.weather.model.request.diary.UpdateDiaryRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanWrapperImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class DiaryServiceImpl implements DiaryService {
    private final DiaryRepository diaryRepository;
    private final WeatherRepository weatherRepository;
    private final UserService userService;
    @Override
    public void createDiary(CreateDiaryRequest request) {
        UserInfoResponse userInfo = userService.findUserInfo();

        if(!Objects.equals(request.getUserId(),userInfo.getUserId())) {
            throw new CustomException(ErrorEnum.USER_DOES_NOT_MATCH);
        }
        Integer locId = getLocationId(userInfo.getLocId());
        Weather weather = getWeather(locId,request.getDiaryDate());

        Diary.DiaryBuilder diaryBuilder = Diary.builder()
                .diaryContent(request.getDiaryContent())
                .diaryDate(request.getDiaryDate())
                .sleep(request.getSleep())
                .activityCd(request.getActivityCd())
                .imgUrl(request.getImgUrl())
                .userId(request.getUserId())
                .weather(weather)
                .emotionCd(request.getEmotionCd());

        if(weather != null) {
            diaryBuilder.weather(weather);
        }
        diaryRepository.save(diaryBuilder.build());
    }

    @Override
    public DiaryInfoResponse findDiaryByUser(FindDiaryRequest request) {
        String startDate = request.getYearMonth().concat("01");
        String endDate = request.getYearMonth().concat("31");

        UserInfoResponse userInfo = userService.findUserInfo();

        if(Objects.equals(request.getUserId(), userInfo.getUserId())) {
            List<Diary> diaries = diaryRepository.findAllByUserIdAndDiaryDateBetween(request.getUserId(), startDate, endDate);
            log.info("diaryInfo : {}", diaries);

            List<DiaryInfo> diaryInfoList = diaries.stream()
                    .map(DiaryInfo::toDto)
                    .peek(this::processDiaryInfo)
                    .collect(Collectors.toList());
            // 수면 시간 평균값 구하기
            int sleepAvg = calculateAverageSleep(diaryInfoList);

            return DiaryInfoResponse.builder()
                    .diaryInfoList(diaryInfoList)
                    .sleepAvg(sleepAvg)
                    .build();
        }else {
            throw new CustomException(ErrorEnum.USER_DOES_NOT_MATCH);
        }
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
                .emotionNm(EmotionEnum.getEmotionNmByCd(diary.getEmotionCd()))
                .activityCd(diary.getActivityCd())
                .activityNm(ActivityEnum.getActivityNmByCd(diary.getActivityCd()))
                .locId(diary.getWeather().getLocId())
                .temp(diary.getWeather().getTemp())
                .wthCond(diary.getWeather().getWthCond())
                .wthCondDesc(diary.getWeather().getWthCondDesc())
                .wthDate(diary.getWeather().getWthDate())
                .wthIconUrl(diary.getWeather().getWthIconUrl())
                .build()).orElseThrow(() -> new CustomException(ErrorEnum.CAN_NOT_FOUND_DIARY));
    }

    @Override
    @Transactional
    public void updateDiaryById(UpdateDiaryRequest request) {
        Diary diary = diaryRepository.findById(request.getDiaryId()).orElseThrow(() -> new CustomException(ErrorEnum.CAN_NOT_FOUND_DIARY));
        BeanWrapperImpl wrapper = new BeanWrapperImpl(request);
        String[] properties = {"diaryContent", "sleep", "activityCd", "emotionCd", "imgUrl"};

        for (String propertyName : properties) {
            Object value = wrapper.getPropertyValue(propertyName);
            if (value != null) {
                setProperty(diary, propertyName, value);
            }
        }
    }

    @Override
    @Transactional
    public void deletDiaryById(Long diaryId) {
        diaryRepository.deleteById(diaryId);
    }

    private Integer getLocationId(Integer locId) {
        for(LocationEnum lc : LocationEnum.values()) {
            if(lc.getLocId().equals(locId)) {
                return lc.getLocId();
            }
        }
        return null;
    }

    private Weather getWeather(Integer locId,String date) {
        List<Weather> weatherList = weatherRepository.findByLocIdAndWthDate(locId, date);
        if(!CollectionUtils.isEmpty(weatherList)) {
            return weatherList.get(0);
        }
        return null;
    }

    private void setProperty(Diary diary, String propertyName, Object value) {
        BeanWrapperImpl wrapper = new BeanWrapperImpl(diary);
        wrapper.setPropertyValue(propertyName, value);
    }

    private void processDiaryInfo(DiaryInfo info) {
        if (info.getActivityCd() != null) {
            info.setActivityNm(ActivityEnum.getActivityNmByCd(info.getActivityCd()));
        }
        if (info.getEmotionCd() != null) {
            info.setEmotionNm(EmotionEnum.getEmotionNmByCd(info.getEmotionCd()));
        }
    }

    private int calculateAverageSleep(List<DiaryInfo> diaryInfoList) {
        return diaryInfoList.isEmpty() ? 0 :
                (int) diaryInfoList.stream()
                        .filter(sleep -> sleep.getSleep() != null)
                        .mapToInt(DiaryInfo::getSleep)
                        .average()
                        .orElse(0);
    }

    private static String getToday() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        return sdf.format(Calendar.getInstance().getTime());
    }
}
