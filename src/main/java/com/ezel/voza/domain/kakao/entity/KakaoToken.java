package com.ezel.voza.domain.kakao.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.redis.core.RedisHash;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@RedisHash("KakaoToken")
public class KakaoToken {

    private Long userId;

    private String accessToken;
}
