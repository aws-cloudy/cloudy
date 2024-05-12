package com.s207.cloudy.global.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableJpaRepositories(basePackages = "com.s207.cloudy.domain.recommend")

public class JpaConfig {
}
