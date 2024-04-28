package com.zerobase.weather.repository;

import com.zerobase.weather.entity.Diary;
import com.zerobase.weather.model.DiaryInfo;
import com.zerobase.weather.model.request.diary.FindDiaryRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DiaryRepository extends JpaRepository<Diary,Long> {
    List<Diary> findAllByUserIdAndDiaryDateBetween(Long userId, String strDate, String endDate);
}
