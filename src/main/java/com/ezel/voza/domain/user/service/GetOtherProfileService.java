package com.ezel.voza.domain.user.service;

import com.ezel.voza.domain.user.presentation.dto.response.ProfileResponse;

public interface GetOtherProfileService {

    ProfileResponse execute(Long userId);
}
