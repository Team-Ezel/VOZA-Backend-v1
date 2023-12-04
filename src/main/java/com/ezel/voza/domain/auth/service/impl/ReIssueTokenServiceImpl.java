package com.ezel.voza.domain.auth.service.impl;

import com.ezel.voza.domain.auth.entity.RefreshToken;
import com.ezel.voza.domain.auth.presentation.dto.response.NewTokenResponse;
import com.ezel.voza.domain.auth.repository.RefreshTokenRepository;
import com.ezel.voza.domain.auth.service.ReIssueTokenService;
import com.ezel.voza.global.annotation.ServiceWithTransactional;
import com.ezel.voza.global.security.exception.TokenNotVaildException;
import com.ezel.voza.global.security.jwt.TokenProvider;
import lombok.RequiredArgsConstructor;

import java.time.ZonedDateTime;

@ServiceWithTransactional
@RequiredArgsConstructor
public class ReIssueTokenServiceImpl implements ReIssueTokenService {

    private final RefreshTokenRepository refreshTokenRepository;

    private final TokenProvider tokenProvider;

    @Override
    public NewTokenResponse execute(String refreshToken) {

        String refresh = tokenProvider.parseToken(refreshToken);

        if (refresh == null) {
            throw new TokenNotVaildException();
        }

        String email = tokenProvider.exactEmailFromRefreshToken(refresh);

        String newAccessToken = tokenProvider.generateAccessToken(email);

        String newRefreshToken = tokenProvider.generateRefreshToken(email);

        ZonedDateTime accessExp = tokenProvider.accessExpiredTime();

        ZonedDateTime refreshExp = tokenProvider.refreshExpiredTime();

        RefreshToken newToken = new RefreshToken(email, newRefreshToken, tokenProvider.getTokenTimeProperties().getRefreshTime());

        refreshTokenRepository.save(newToken);

        return NewTokenResponse.builder()
                .accessToken(newAccessToken)
                .refreshToken(newRefreshToken)
                .accessExp(accessExp)
                .refreshExp(refreshExp)
                .build();
    }
}
