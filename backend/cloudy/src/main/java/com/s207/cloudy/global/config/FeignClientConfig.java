package com.s207.cloudy.global.config;


import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableFeignClients(basePackages ="com.s207.cloudy.infra")
public class FeignClientConfig {
}
