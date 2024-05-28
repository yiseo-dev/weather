package com.pys.weather.controller;

import com.pys.weather.config.Response;
import com.pys.weather.model.request.file.UploadFileRequest;
import com.pys.weather.model.response.file.PresignedUrlResponse;
import com.pys.weather.service.file.FileService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
@Slf4j
@RestController
@RequestMapping("/v1/files")
@RequiredArgsConstructor
public class FileController {
    private final FileService fileService;

    @Operation(summary = "서명된 url 요청", description = "이미지 업로드 전 권한 url 받기 위한 api", tags = { "File Controller" })
    @GetMapping("/presigned-url/upload")
    public ResponseEntity<Response> generatePresignedUrl(UploadFileRequest request) {
        log.info("=================[START] generatePresignedUrl==================");
        log.info("request: {}", request);

        PresignedUrlResponse presignedUrl = fileService.generatePresignedUrl(request);

        log.info("response: {}", presignedUrl);
        log.info("=================[END] generatePresignedUrl==================");
        return ResponseEntity.ok(Response.builder()
                        .data(presignedUrl)
                        .build());
    }

//    @Operation(summary = "이미지 조회 url 요청", description = "이미지 조회 전 권한 url api")
//    @GetMapping("/presigend-url/view")
//    public ResponseEntity<Response> generatePresignedUrlForView() {
//
//    }
}
