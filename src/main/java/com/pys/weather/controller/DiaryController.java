package com.pys.weather.controller;

import com.pys.weather.config.Response;
import com.pys.weather.model.DiaryInfo;
import com.pys.weather.model.request.diary.CreateDiaryRequest;
import com.pys.weather.model.request.diary.FindDiaryRequest;
import com.pys.weather.model.request.diary.UpdateDiaryRequest;
import com.pys.weather.model.response.diary.DiaryInfoResponse;
import com.pys.weather.service.diary.DiaryService;
import io.swagger.v3.oas.annotations.Operation;
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
    @Operation(summary = "일기 작성", description = "일기 작성하는 API", tags = { "DiaryController" })
    public ResponseEntity<Response> createDiary(@RequestBody CreateDiaryRequest createDiary) {
        log.info("=================[START] createDiary==================");
        log.info("request: {}", createDiary);

        diaryService.createDiary(createDiary);

        log.info("=================[END] createDiary==================");
        return ResponseEntity.ok(Response.builder()
                        .data(HttpStatus.CREATED)
                        .build());
    }

    @GetMapping("/user/{userId}")
    @Operation(summary = "일기 목록 조회", description = "일기 1개월치 목록 조회 API", tags = { "DiaryController" })
    public ResponseEntity<Response> findDiaryByUser(@PathVariable(value = "userId") Long userId, FindDiaryRequest request) {
        request.setUserId(userId);
        log.info("=================[START] findDiaryByUser==================");
        log.info("request: {}", request);

        DiaryInfoResponse response = diaryService.findDiaryByUser(request);

        log.info("response: {}", response);
        log.info("=================[END] findDiaryByUser==================");
        return ResponseEntity.ok(Response.builder()
                        .data(response)
                        .build());
    }

    @GetMapping("/diary/{diaryId}")
    @Operation(summary = "일기 상세 조회", description = "일기 상세 조회 api", tags = { "DiaryController" })
    public ResponseEntity<Response> findDiaryById(@PathVariable(value = "diaryId") Long diaryId) {
        log.info("=================[START] findDiaryById==================");
        log.info("diaryId: {}", diaryId);

        DiaryInfo response = diaryService.findDiaryById(diaryId);

        log.info("response: {}", response);
        log.info("=================[END] findDiaryById==================");

        return ResponseEntity.ok(Response.builder()
                        .data(response)
                        .build());
    }

    @PatchMapping("/diary/{diaryId}")
    @Operation(summary = "일기 수정", description = "일기 수정 api", tags = { "DiaryController" })
    public ResponseEntity<Response> updateDiaryById(@PathVariable(value = "diaryId") Long diaryId
                                                   ,@RequestBody UpdateDiaryRequest request) {
        request.setDiaryId(diaryId);
        log.info("=================[START] updateDiaryById==================");
        log.info("request: {}", request);

        diaryService.updateDiaryById(request);

        log.info("=================[END] updateDiaryById==================");
        return ResponseEntity.ok(Response.builder()
                        .data(HttpStatus.OK)
                        .build());
    }

    @DeleteMapping("/diary/{diaryId}")
    @Operation(summary = "일기 삭제", description = "일기 삭제", tags = { "DiaryController" })
    public ResponseEntity<Response> deleteDiaryById(@PathVariable(value = "diaryId") Long diaryId) {
        log.info("=================[START] deleteDiaryById==================");
        log.info("diaryId: {}", diaryId);

        diaryService.deletDiaryById(diaryId);

        log.info("=================[END] deleteDiaryById==================");
        return ResponseEntity.ok(Response.builder().data(HttpStatus.OK).build());
    }

}
