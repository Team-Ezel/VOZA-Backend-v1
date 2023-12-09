package com.ezel.voza.domain.auth.service;

public interface UserLogoutService {

    void execute(String accessToken, String refreshToken);
}
