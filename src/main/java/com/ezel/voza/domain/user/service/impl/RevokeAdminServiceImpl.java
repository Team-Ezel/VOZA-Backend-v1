package com.ezel.voza.domain.user.service.impl;

import com.ezel.voza.domain.auth.exception.UserNotFoundException;
import com.ezel.voza.domain.user.entity.User;
import com.ezel.voza.domain.user.entity.enums.Role;
import com.ezel.voza.domain.user.exception.YouNotAdminException;
import com.ezel.voza.domain.user.repository.UserRepository;
import com.ezel.voza.domain.user.service.RevokeAdminService;
import com.ezel.voza.global.util.UserUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RevokeAdminServiceImpl implements RevokeAdminService {

    private final UserUtil userUtil;

    private final UserRepository userRepository;

    @Override
    public void execute(String email) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(UserNotFoundException::new);

        if (userUtil.currentUser().getRole() != Role.ROLE_ADMIN) {
            throw new YouNotAdminException();
        }

        user.updateRole(Role.ROLE_USER);

        userRepository.save(user);
    }
}
