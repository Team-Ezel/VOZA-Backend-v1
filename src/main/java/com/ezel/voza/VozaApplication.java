package com.ezel.voza;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.redis.repository.configuration.EnableRedisRepositories;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@SpringBootApplication
@EnableRedisRepositories
@EnableConfigurationProperties
@EnableCaching
@ConfigurationPropertiesScan
@EnableJpaRepositories
@EnableWebMvc
@EnableJpaAuditing
public class VozaApplication {

    public static void main(String[] args) {
        SpringApplication.run(VozaApplication.class, args);
    }

}