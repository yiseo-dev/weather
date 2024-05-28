package com.pys.weather.repository;

import com.pys.weather.entity.Weather;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WeatherRepository extends JpaRepository<Weather,Long> {
    List<Weather> findByLocIdAndWthDate(Integer locId, String wthDate);
}
