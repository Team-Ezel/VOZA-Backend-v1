package com.ezel.voza.domain.auth.service.impl;

import com.ezel.voza.domain.auth.entity.RefreshToken;
import com.ezel.voza.domain.auth.exception.UserNotFoundException;
import com.ezel.voza.domain.auth.presentation.dto.response.LoginResponse;
import com.ezel.voza.domain.auth.repository.RefreshTokenRepository;
import com.ezel.voza.domain.auth.service.UserLoginService;
import com.ezel.voza.domain.user.entity.User;
import com.ezel.voza.domain.user.repository.UserRepository;
import com.ezel.voza.global.security.jwt.TokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserLoginServiceImpl implements UserLoginService {

    private final TokenProvider tokenProvider;
    private final RefreshTokenRepository refreshTokenRepository;
    private final UserRepository userRepository;

    @Override
    public LoginResponse execute(String email) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(UserNotFoundException::new);

        String accessToken = tokenProvider.generateAccessToken(email);
        String refreshToken = tokenProvider.generateRefreshToken(email);
        RefreshToken tokenRedis = new RefreshToken(email, refreshToken, tokenProvider.getTokenTimeProperties().getRefreshTime());

        refreshTokenRepository.save(tokenRedis);

        return LoginResponse.builder()
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .AccessExpiredAt(tokenProvider.accessExpiredTime())
                .build();
    }
}
