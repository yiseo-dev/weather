package com.pys.weather.service.file;

import com.pys.weather.model.request.file.UploadFileRequest;
import com.pys.weather.model.response.file.PresignedUrlResponse;

public interface FileService {
    PresignedUrlResponse generatePresignedUrl(UploadFileRequest request);
}
