package com.zerobase.weather.config;

import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableFeignClients("com.zerobase.weather.feign")
public class FeignClientsConfig {
}
