package com.ezel.voza.global.webhook.util;

import lombok.RequiredArgsConstructor;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Objects;

@Component
@RequiredArgsConstructor
public class BotUtil {

    private final OkHttpClient okHttpClient;

    @Value("${discord.webhook.url}")
    private String discordWebhookUrl;

    private final Logger log = LoggerFactory.getLogger(this.getClass().getSimpleName());

    public void sendDiscordMessage(String botMessage) {

        RequestBody requestBody = RequestBody.create(botMessage, MediaType.parse("application/json; charset=utf-8"));

        Request request = new Request.Builder()
                .url(discordWebhookUrl)
                .post(requestBody)
                .build();

        try(okhttp3.Response response = okHttpClient.newCall(request).execute()) {

            if(response.isSuccessful()) {

                log.trace("요청 사항 전달 완료");
            } else {
                log.error(Objects.requireNonNull(response.body()).string());
                log.error(response.code() + " 발생");
            }
        } catch (IOException e) {
            throw new RuntimeException("개 망함");
        }
    }

    public String   message(String reportName, String reportType) {

        return "{\n" +
                "    \"content\": \"종봇의 신고 전달\",\n" +
                "    \"embeds\": [\n" +
                "        {\n" +
                "            \"title\": \"종봇의 신고 전달\",\n" +
                "            \"color\": 5725911,\n" +
                "            \"fields\": [\n" +
                "                {\n" +
                "                    \"name\": \"신고 제목\",\n" +
                "                    \"value\": \"" + reportName + "\",\n" +
                "                    \"inline\": true\n" +
                "                },\n" +
                "                {\n" +
                "                    \"name\": \"신고유형(User, Group)\",\n" +
                "                    \"value\": \"" + reportType + "\",\n" +
                "                    \"inline\": true\n" +
                "                }\n" +
                "            ]\n" +
                "        }\n" +
                "    ],\n" +
                "    \"attachments\": []\n" +
                "}".trim();
    }
}
