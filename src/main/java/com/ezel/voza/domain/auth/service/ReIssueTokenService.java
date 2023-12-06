package com.ezel.voza.domain.auth.service;

import com.ezel.voza.domain.auth.presentation.dto.response.NewTokenResponse;

public interface ReIssueTokenService {

    NewTokenResponse execute(String refreshToken);
}
