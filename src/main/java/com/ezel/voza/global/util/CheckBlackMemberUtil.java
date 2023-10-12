package com.ezel.voza.global.util;

import com.ezel.voza.domain.group.entity.Group;
import com.ezel.voza.domain.group.exception.KickOutUserException;
import com.ezel.voza.domain.member.entity.BlackMember;
import com.ezel.voza.domain.member.repository.BlackMemberRepository;
import com.ezel.voza.domain.user.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
@RequiredArgsConstructor
public class CheckBlackMemberUtil {

    private final BlackMemberRepository blackMemberRepository;

    public void check(Group group, User user) {
        BlackMember blackMember = blackMemberRepository.findByGroupAndUser(group, user);

        if (blackMember != null) {
            LocalDateTime currentTime = LocalDateTime.now();

            if (currentTime.isBefore(blackMember.getKickOutTime())) {
                throw new KickOutUserException();
            }
        }
    }
}
