package com.pys.weather.config;

import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableFeignClients("com.pys.weather.feign")
public class FeignClientsConfig {
}
