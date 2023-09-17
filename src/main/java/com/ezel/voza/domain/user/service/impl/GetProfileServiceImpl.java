package com.ezel.voza.domain.user.service.impl;

import com.ezel.voza.domain.user.entity.User;
import com.ezel.voza.domain.user.presentation.dto.response.ProfileResponse;
import com.ezel.voza.domain.user.service.GetProfileService;
import com.ezel.voza.global.util.UserUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GetProfileServiceImpl implements GetProfileService {

    private final UserUtil userUtil;

    @Override
    public ProfileResponse execute() {
        User user = userUtil.currentUser();

        return ProfileResponse.builder()
                .email(user.getEmail())
                .profileUrl(user.getProfileUrl())
                .nickName(user.getNickName())
                .build();
    }
}
