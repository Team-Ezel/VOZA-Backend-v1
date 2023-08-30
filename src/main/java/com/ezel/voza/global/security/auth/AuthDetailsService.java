package com.ezel.voza.global.security.auth;

import com.ezel.voza.domain.user.entity.User;
import com.ezel.voza.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String nickName) throws UsernameNotFoundException {

        //TODO : 후에 custom exception 으로 교체

        User user = userRepository.findByNickName(nickName)
                .orElseThrow(RuntimeException::new);

        return new AuthDetails(user);
    }
}
