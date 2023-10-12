package com.ezel.voza.domain.kakao.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.TimeToLive;
import org.springframework.data.redis.core.index.Indexed;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@RedisHash("KakaoToken")
public class KakaoToken {

    @Id
    private Long userId;

    @Indexed
    private String accessToken;

    @Indexed
    private String refreshToken;

    @TimeToLive
    private Long timeToLive;

}
