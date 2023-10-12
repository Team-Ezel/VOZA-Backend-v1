package com.ezel.voza.global.webhook.config;

import okhttp3.OkHttpClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class WebHookConfig {

    private final OkHttpClient okHttpClient = new OkHttpClient();

    @Bean
    public OkHttpClient httpClient() {
        return okHttpClient;
    }

}
