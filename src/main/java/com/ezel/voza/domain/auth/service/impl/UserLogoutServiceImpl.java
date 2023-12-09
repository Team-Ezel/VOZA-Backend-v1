package com.ezel.voza.domain.auth.service.impl;

import com.ezel.voza.domain.auth.entity.RefreshToken;
import com.ezel.voza.domain.auth.repository.RefreshTokenRepository;
import com.ezel.voza.domain.auth.service.UserLogoutService;
import com.ezel.voza.global.annotation.ServiceWithTransactional;
import com.ezel.voza.global.redis.util.RedisUtil;
import com.ezel.voza.global.security.jwt.TokenProvider;
import lombok.RequiredArgsConstructor;

@ServiceWithTransactional
@RequiredArgsConstructor
public class UserLogoutServiceImpl implements UserLogoutService {

    private final RefreshTokenRepository refreshTokenRepository;

    private final TokenProvider tokenProvider;

    private final RedisUtil redisUtil;

    @Override
    public void execute(String accessToken, String refreshToken) {

        String access = tokenProvider.parseToken(accessToken);

        String refresh = tokenProvider.parseToken(refreshToken);

        RefreshToken userRefreshToken = refreshTokenRepository.findByRefreshToken(refresh);

        refreshTokenRepository.delete(userRefreshToken);

        Long expiration = tokenProvider.getExpiration(access);

        redisUtil.setBlackList(access, "logout", expiration);
    }
}
