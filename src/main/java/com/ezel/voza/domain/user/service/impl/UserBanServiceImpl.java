package com.ezel.voza.domain.user.service.impl;

import com.ezel.voza.domain.auth.entity.BlackUser;
import com.ezel.voza.domain.auth.exception.UserNotFoundException;
import com.ezel.voza.domain.auth.repository.BlackUserRepository;
import com.ezel.voza.domain.user.entity.User;
import com.ezel.voza.domain.user.entity.enums.Banned;
import com.ezel.voza.domain.user.entity.enums.Role;
import com.ezel.voza.domain.user.exception.YouNotAdminException;
import com.ezel.voza.domain.user.repository.UserRepository;
import com.ezel.voza.domain.user.service.UserBanService;
import com.ezel.voza.global.util.UserUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserBanServiceImpl implements UserBanService {

    private final UserUtil userUtil;
    private final UserRepository userRepository;
    private final BlackUserRepository blackUserRepository;

    @Override
    public void execute(String email) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(UserNotFoundException::new);

        if (userUtil.currentUser().getRole() != Role.ROLE_ADMIN) {
            throw new YouNotAdminException();
        }

        BlackUser blackUser = BlackUser.builder()
                        .user(user).build();

        blackUserRepository.save(blackUser);

        user.updateBanned(Banned.BAN);

        userRepository.save(user);
    }
}
