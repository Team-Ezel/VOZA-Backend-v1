package com.ezel.voza.domain.user.service;

import com.ezel.voza.domain.user.presentation.dto.request.SignUpRequest;

public interface UserSignupService {
    void execute(SignUpRequest signUpRequest);
}
