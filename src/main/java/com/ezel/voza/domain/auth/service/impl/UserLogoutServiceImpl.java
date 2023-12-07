package com.ezel.voza.domain.auth.service.impl;

import com.ezel.voza.domain.auth.exception.UserNotFoundException;
import com.ezel.voza.domain.auth.repository.RefreshTokenRepository;
import com.ezel.voza.domain.auth.service.UserLogoutService;
import com.ezel.voza.domain.user.entity.User;
import com.ezel.voza.global.annotation.ServiceWithTransactional;
import com.ezel.voza.global.util.UserUtil;
import lombok.RequiredArgsConstructor;

@ServiceWithTransactional
@RequiredArgsConstructor
public class UserLogoutServiceImpl implements UserLogoutService {

    private final UserUtil userUtil;

    private final RefreshTokenRepository refreshTokenRepository;

    @Override
    public void execute() {

        User user = userUtil.currentUser();

        refreshTokenRepository.findById(user.getEmail())
                .ifPresentOrElse(
                        refreshTokenRepository::delete,
                        () -> {
                            throw new UserNotFoundException();
                        }
                );
    }
}
