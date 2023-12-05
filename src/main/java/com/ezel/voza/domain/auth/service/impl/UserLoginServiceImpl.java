package com.ezel.voza.domain.auth.service.impl;

import com.ezel.voza.domain.auth.entity.RefreshToken;
import com.ezel.voza.domain.auth.exception.UserNotFoundException;
import com.ezel.voza.domain.auth.exception.YouBanException;
import com.ezel.voza.domain.auth.presentation.dto.response.LoginResponse;
import com.ezel.voza.domain.auth.repository.BlackUserRepository;
import com.ezel.voza.domain.auth.repository.RefreshTokenRepository;
import com.ezel.voza.domain.auth.service.UserLoginService;
import com.ezel.voza.domain.user.entity.User;
import com.ezel.voza.domain.user.repository.UserRepository;
import com.ezel.voza.global.annotation.ServiceWithTransactional;
import com.ezel.voza.global.security.jwt.TokenProvider;
import lombok.RequiredArgsConstructor;

@ServiceWithTransactional
@RequiredArgsConstructor
public class UserLoginServiceImpl implements UserLoginService {

    private final TokenProvider tokenProvider;
    private final RefreshTokenRepository refreshTokenRepository;
    private final UserRepository userRepository;
    private final BlackUserRepository blackUserRepository;

    @Override
    public LoginResponse execute(String email) {

        User user = userRepository.findByEmail(email)
                .orElseThrow(UserNotFoundException::new);

        if (blackUserRepository.existsByUser(user)) {
            throw new YouBanException();
        }

        String accessToken = tokenProvider.generateAccessToken(email);
        String refreshToken = tokenProvider.generateRefreshToken(email);
        RefreshToken tokenRedis = new RefreshToken(email, refreshToken, tokenProvider.getTokenTimeProperties().getRefreshTime());

        refreshTokenRepository.save(tokenRedis);

        return LoginResponse.builder()
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .accessExpiredAt(tokenProvider.accessExpiredTime())
                .refreshExpiredAt(tokenProvider.refreshExpiredTime())
                .build();
    }
}
