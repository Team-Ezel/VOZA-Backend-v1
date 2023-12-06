package com.ezel.voza.domain.auth.service.impl;

import com.ezel.voza.domain.auth.entity.RefreshToken;
import com.ezel.voza.domain.auth.presentation.dto.response.NewTokenResponse;
import com.ezel.voza.domain.auth.repository.RefreshTokenRepository;
import com.ezel.voza.domain.auth.service.ReIssueTokenService;
import com.ezel.voza.global.annotation.ServiceWithTransactional;
import com.ezel.voza.global.security.exception.TokenNotVaildException;
import com.ezel.voza.global.security.jwt.TokenProvider;
import com.ezel.voza.global.security.jwt.properties.JwtProperties;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import lombok.RequiredArgsConstructor;

import java.time.ZonedDateTime;

@ServiceWithTransactional
@RequiredArgsConstructor
public class ReIssueTokenServiceImpl implements ReIssueTokenService {

    private final RefreshTokenRepository refreshTokenRepository;

    private final TokenProvider tokenProvider;

    private final JwtProperties jwtProperties;

    @Override
    public NewTokenResponse execute(String refreshToken) {

        String refresh = tokenProvider.parseToken(refreshToken);

        validateRefreshToken(refresh);

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

    private void validateRefreshToken(String refreshToken) {

        try {
            Claims claims = Jwts.parserBuilder()
                    .setSigningKey(jwtProperties.getRefreshSecret())
                    .build()
                    .parseClaimsJws(refreshToken)
                    .getBody();
        } catch (Exception e) {
            throw new TokenNotVaildException();
        }

        RefreshToken token = refreshTokenRepository.findByRefreshToken(refreshToken);

        if (token == null) {
            throw new TokenNotVaildException();
        }
    }
}

