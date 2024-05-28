package com.pys.weather.config;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
public class AWSS3Config {
    @Value("${amazon.aws.accessKey}")
    private String accessKey;
    @Value("${amazon.aws.secretKey}")
    private String accessSecretKey;
    @Value("${amazon.aws.region}")
    private String s3Region;
    
    // AWS 자격 증명을 생성하는 빈
    @Bean
    @Primary
    public BasicAWSCredentials awsCredentialsProvider() {
        BasicAWSCredentials basicAWSCredentials = new BasicAWSCredentials(accessKey,accessSecretKey);
        return basicAWSCredentials;
    }

    // S3 객체에 대한 사전 서명된 URL을 생성하여 특정 시간 동안 제한된 엑세스를 제공함
    @Bean
    public AmazonS3 amazonS3() {
        return AmazonS3ClientBuilder.standard()
                .withRegion(s3Region)
                .withCredentials(new AWSStaticCredentialsProvider(awsCredentialsProvider()))
                .build();
    }
}
