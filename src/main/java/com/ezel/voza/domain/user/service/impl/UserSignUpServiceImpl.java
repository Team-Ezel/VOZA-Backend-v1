package com.ezel.voza.domain.user.service.impl;

import com.ezel.voza.domain.user.entity.User;
import com.ezel.voza.domain.user.entity.enums.Banned;
import com.ezel.voza.domain.user.entity.enums.Role;
import com.ezel.voza.domain.user.presentation.dto.request.SignUpRequest;
import com.ezel.voza.domain.user.repository.UserRepository;
import com.ezel.voza.domain.user.service.UserSignupService;
import com.ezel.voza.global.annotation.ServiceWithTransactional;
import lombok.RequiredArgsConstructor;

@ServiceWithTransactional
@RequiredArgsConstructor
public class UserSignUpServiceImpl implements UserSignupService {

    private final UserRepository userRepository;

    @Override
    public void execute(SignUpRequest signUpRequest) {
        User user = User.builder()
                .email(signUpRequest.getEmail())
                .profileUrl(signUpRequest.getProfileUrl())
                .nickName(signUpRequest.getNickName())
                .banned(Banned.UNBAN)
                .role(Role.ROLE_USER)
                .build();

        userRepository.save(user);
    }
}
