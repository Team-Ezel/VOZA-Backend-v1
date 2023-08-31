package com.ezel.voza.domain.auth.service;

import com.ezel.voza.domain.auth.presentation.dto.response.LoginResponse;

public interface UserLoginService {

    LoginResponse execute(String email);
}
