package com.ezel.voza.domain.member.service.impl;

import com.ezel.voza.domain.group.entity.Group;
import com.ezel.voza.domain.group.exception.NotExistGroupException;
import com.ezel.voza.domain.member.entity.BlackMember;
import com.ezel.voza.domain.member.entity.enums.KickOutTime;
import com.ezel.voza.domain.member.exception.NotManagerException;
import com.ezel.voza.domain.member.repository.BlackMemberRepository;
import com.ezel.voza.domain.member.service.KickOutMemberService;
import com.ezel.voza.domain.user.entity.User;
import com.ezel.voza.global.annotation.ServiceWithTransactional;
import com.ezel.voza.global.util.GroupUtil;
import com.ezel.voza.global.util.UserUtil;
import lombok.RequiredArgsConstructor;

import java.util.Objects;

@ServiceWithTransactional
@RequiredArgsConstructor
public class KickOutMemberServiceImpl implements KickOutMemberService {

    private final GroupUtil groupUtil;

    private final UserUtil userUtil;

    private final BlackMemberRepository blackMemberRepository;

    @Override
    public void execute(Long groupId, Long userId, String kickOutTime) {

        Group group = groupUtil.findGroupById(groupId);

        roleCheck(group, userUtil.currentUser());

        User kickOutMember = userUtil.findUserById(userId);

        if (!group.getMembers().containsKey(kickOutMember)) {
            throw new NotExistGroupException();
        }

        BlackMember blackMember = BlackMember.builder()
                .group(group)
                .user(kickOutMember)
                .build();

        blackMember.setKickOutTime(KickOutTime.from(kickOutTime));

        blackMemberRepository.save(blackMember);

        group.deleteMember(kickOutMember);
    }

    private void roleCheck(Group group, User user) {
        if (!Objects.equals(group.getLeaderName(), user.getNickName())) {
            throw new NotManagerException();
        }
    }
}
