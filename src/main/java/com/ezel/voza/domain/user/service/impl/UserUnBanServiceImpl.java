package com.ezel.voza.domain.user.service.impl;

import com.ezel.voza.domain.auth.entity.BlackUser;
import com.ezel.voza.domain.auth.exception.UserNotFoundException;
import com.ezel.voza.domain.auth.repository.BlackUserRepository;
import com.ezel.voza.domain.user.entity.User;
import com.ezel.voza.domain.user.entity.enums.Banned;
import com.ezel.voza.domain.user.entity.enums.Role;
import com.ezel.voza.domain.user.exception.NotBlackUserException;
import com.ezel.voza.domain.user.exception.YouNotAdminException;
import com.ezel.voza.domain.user.repository.UserRepository;
import com.ezel.voza.domain.user.service.UserUnBanService;
import com.ezel.voza.global.util.UserUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserUnBanServiceImpl implements UserUnBanService {

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

        user.updateBanned(Banned.UNBAN);

        userRepository.save(user);

        BlackUser blackUser = blackUserRepository.findByUserEmail(email)
                        .orElseThrow(NotBlackUserException::new);

        blackUserRepository.delete(blackUser);
    }
}
