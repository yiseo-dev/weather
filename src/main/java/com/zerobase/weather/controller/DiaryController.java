package com.zerobase.weather.controller;

import com.zerobase.weather.config.Response;
import com.zerobase.weather.model.request.diary.CreateDiaryRequest;
import com.zerobase.weather.model.request.diary.FindDiaryRequest;
import com.zerobase.weather.model.response.diary.DiaryInfoResponse;
import com.zerobase.weather.service.DiaryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/diaries")
public class DiaryController {
    private final DiaryService diaryService;

    @PostMapping("/")
    public ResponseEntity<Response> createDiary(@RequestBody CreateDiaryRequest createDiary) {
        diaryService.createDiary(createDiary);
        return ResponseEntity.ok(Response.builder()
                        .data(HttpStatus.CREATED)
                        .build());
    }

    @GetMapping("/user")
    public ResponseEntity<Response> findDiaryByUser(FindDiaryRequest request) {
        log.info("=================[START] findDiaryByUser==================");
        log.info("request: {}", request);
        DiaryInfoResponse response = diaryService.findDiaryByUser(request);

        log.info("response: {}", response);
        log.info("=================[END] findDiaryByUser==================");
        return ResponseEntity.ok(Response.builder()
                        .data(response)
                        .build());
    }

}
