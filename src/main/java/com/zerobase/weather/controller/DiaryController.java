package com.zerobase.weather.controller;

import com.zerobase.weather.config.Response;
import com.zerobase.weather.model.DiaryInfo;
import com.zerobase.weather.model.request.diary.CreateDiaryRequest;
import com.zerobase.weather.model.request.diary.FindDiaryRequest;
import com.zerobase.weather.model.request.diary.UpdateDiaryRequest;
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
        log.info("=================[START] createDiary==================");
        log.info("request: {}", createDiary);

        diaryService.createDiary(createDiary);

        log.info("=================[END] createDiary==================");
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

    @GetMapping("/diary")
    public ResponseEntity<Response> findDiaryById(@RequestParam Long diaryId) {
        log.info("=================[START] findDiaryById==================");
        log.info("diaryId: {}", diaryId);

        DiaryInfo response = diaryService.findDiaryById(diaryId);

        log.info("response: {}", response);
        log.info("=================[END] findDiaryById==================");

        return ResponseEntity.ok(Response.builder()
                        .data(response)
                        .build());
    }

    @PatchMapping("/diary")
    public ResponseEntity<Response> updateDiaryById(@RequestBody UpdateDiaryRequest request) {
        log.info("=================[START] updateDiaryById==================");
        log.info("request: {}", request);

        diaryService.updateDiaryById(request);

        log.info("=================[END] updateDiaryById==================");
        return ResponseEntity.ok(Response.builder()
                        .data(HttpStatus.OK)
                        .build());
    }

    @DeleteMapping("/diary")
    public ResponseEntity<Response> deleteDiaryById(@RequestParam Long diaryId) {
        log.info("=================[START] deleteDiaryById==================");
        log.info("diaryId: {}", diaryId);

        diaryService.deletDiaryById(diaryId);

        log.info("=================[END] deleteDiaryById==================");
        return ResponseEntity.ok(Response.builder().data(HttpStatus.OK).build());
    }

}
