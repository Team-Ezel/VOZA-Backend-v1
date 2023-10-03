package com.ezel.voza.domain.group.service.impl;

import com.ezel.voza.domain.group.entity.Group;
import com.ezel.voza.domain.group.entity.GroupInvite;
import com.ezel.voza.domain.group.exception.*;
import com.ezel.voza.domain.group.presentation.dto.request.EnterGroupRequest;
import com.ezel.voza.domain.group.repository.GroupInviteRepository;
import com.ezel.voza.domain.group.repository.GroupRepository;
import com.ezel.voza.domain.group.service.EnterByCodeGroupService;
import com.ezel.voza.domain.member.entity.BlackMember;
import com.ezel.voza.domain.member.repository.BlackMemberRepository;
import com.ezel.voza.domain.user.entity.User;
import com.ezel.voza.global.util.UserUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class EnterByCodeGroupServiceImpl implements EnterByCodeGroupService {

    private final GroupInviteRepository groupInviteRepository;
    private final GroupRepository groupRepository;
    private final UserUtil util;
    private final BlackMemberRepository blackMemberRepository;

    @Override
    public void execute(EnterGroupRequest enterGroupRequest) {

        User user = util.currentUser();

        GroupInvite invite = groupInviteRepository.findById(enterGroupRequest.getEmail())
                .orElseThrow(GroupNotFoundException::new);

        Group group = groupRepository.findById(invite.getGroupId())
                .orElseThrow(GroupNotFoundException::new);

        if (group.getStop()) {
            throw new NotAllowedEnterBanGroupException();
        }

        if (Objects.equals(invite.getInviteCode(), enterGroupRequest.getInviteCode())) {


            if (group.getMembers().containsKey(user)) {
                throw new AlreadyExistGroupException();
            }

            checkBlackMember(group, user);

            group.putMember(user, "member");

            groupRepository.save(group);
        } else {
            throw new MisMatchInviteCodeException();
        }
    }

    public void checkBlackMember(Group group, User user) {
        BlackMember blackMember = blackMemberRepository.findByGroupAndUser(group, user);

        if (blackMember != null) {
            LocalDateTime currentTime = LocalDateTime.now();

            if (currentTime.isBefore(blackMember.getKickOutTime())) {
                throw new KickOutUserException();
            }
        }
    }
}
