package com.zerobase.weather.service.file;

import com.zerobase.weather.model.request.file.UploadFileRequest;
import com.zerobase.weather.model.response.file.PresignedUrlResponse;

public interface FileService {
    PresignedUrlResponse generatePresignedUrl(UploadFileRequest request);
}
