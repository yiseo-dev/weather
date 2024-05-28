package com.pys.weather.service.file.impl;

import com.amazonaws.HttpMethod;
import com.amazonaws.services.s3.Headers;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.GeneratePresignedUrlRequest;
import com.pys.weather.model.request.file.UploadFileRequest;
import com.pys.weather.service.file.FileService;
import com.pys.weather.config.AWSS3Config;
import com.pys.weather.model.response.file.PresignedUrlResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.net.URL;
import java.util.Date;
import java.util.UUID;

@Service
@Slf4j
@RequiredArgsConstructor
public class FileServiceImpl implements FileService {
    @Value("${amazon.aws.bucket}")
    private String bucket;
    private final AWSS3Config awss3Config;
    @Override
    public PresignedUrlResponse generatePresignedUrl(UploadFileRequest request) {
        String fileId = UUID.randomUUID().toString();
        String path = String.format("%s", fileId + "-" + request.getFileName());

        GeneratePresignedUrlRequest generatePresignedUrlRequest = getGeneratePresignedUrlRequest(bucket,path);
        URL url = awss3Config.amazonS3().generatePresignedUrl(generatePresignedUrlRequest);

        return PresignedUrlResponse.builder().presignedUrl(url.toString()).build();
    }

    private GeneratePresignedUrlRequest getGeneratePresignedUrlRequest(String bucket, String fileName) {
        GeneratePresignedUrlRequest generatePresignedUrlRequest = new GeneratePresignedUrlRequest(bucket,fileName)
                .withMethod(HttpMethod.PUT)
                .withExpiration(createExpiration());

        generatePresignedUrlRequest.addRequestParameter(
                Headers.S3_CANNED_ACL,
                CannedAccessControlList.PublicRead.toString()
        );

        return generatePresignedUrlRequest;
    }

    private Date createExpiration(){
        Date expiration = new Date();
        long expTimeMillis = expiration.getTime();
        expTimeMillis += 1000 * 60 * 3;
        expiration.setTime(expTimeMillis);

        log.info("expiration: {}", expiration);

        return expiration;
    }
}
