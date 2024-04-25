package com.zerobase.weather.controller;

import com.zerobase.weather.config.Response;
import com.zerobase.weather.model.request.diary.CreateDiaryRequest;
import com.zerobase.weather.service.DiaryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/diaries")
public class DiaryController {
    private final DiaryService diaryService;

    @PostMapping
    public ResponseEntity<Response> createDiary(@RequestBody CreateDiaryRequest createDiary) {
        return ResponseEntity.ok(Response.builder()
                        .data(HttpStatus.CREATED)
                        .build());
    }

}
