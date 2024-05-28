package com.pys.weather.repository;

import com.pys.weather.entity.Diary;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DiaryRepository extends JpaRepository<Diary,Long> {
    List<Diary> findAllByUserIdAndDiaryDateBetween(Long userId, String strDate, String endDate);
}
