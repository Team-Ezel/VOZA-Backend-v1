package com.ezel.voza.domain.kakao.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.TimeToLive;

import java.time.ZonedDateTime;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@RedisHash("KakaoToken")
public class KakaoToken {

    @Id
    private Long userId;
    private String accessToken;

    @TimeToLive
    private ZonedDateTime timeToLive;

}
