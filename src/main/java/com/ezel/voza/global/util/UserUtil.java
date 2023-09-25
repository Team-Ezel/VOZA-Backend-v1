package com.ezel.voza.global.util;

import com.ezel.voza.domain.auth.exception.UserNotFoundException;
import com.ezel.voza.domain.user.entity.User;
import com.ezel.voza.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserUtil {

    private final UserRepository userRepository;

    public User currentUser() {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        return userRepository.findByNickName(email)
                .orElseThrow(UserNotFoundException::new);
    }

    public User findUserById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(UserNotFoundException::new);
    }
}
