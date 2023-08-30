package com.ezel.voza;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.redis.repository.configuration.EnableRedisRepositories;

@SpringBootApplication
@EnableRedisRepositories
public class VozaApplication {

    public static void main(String[] args) {
        SpringApplication.run(VozaApplication.class, args);
    }

}