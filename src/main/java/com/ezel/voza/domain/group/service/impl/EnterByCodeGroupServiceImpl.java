package com.ezel.voza.domain.group.service.impl;

import com.ezel.voza.domain.group.entity.Group;
import com.ezel.voza.domain.group.entity.GroupInvite;
import com.ezel.voza.domain.group.exception.AlreadyExistGroupException;
import com.ezel.voza.domain.group.exception.GroupNotFoundException;
import com.ezel.voza.domain.group.exception.MisMatchInviteCodeException;
import com.ezel.voza.domain.group.exception.NotAllowedEnterBanGroupException;
import com.ezel.voza.domain.group.presentation.dto.request.EnterGroupRequest;
import com.ezel.voza.domain.group.repository.GroupInviteRepository;
import com.ezel.voza.domain.group.repository.GroupRepository;
import com.ezel.voza.domain.group.service.EnterByCodeGroupService;
import com.ezel.voza.domain.user.entity.User;
import com.ezel.voza.global.annotation.ServiceWithTransactional;
import com.ezel.voza.global.util.CheckBlackMemberUtil;
import com.ezel.voza.global.util.UserUtil;
import lombok.RequiredArgsConstructor;

import java.util.Objects;

@ServiceWithTransactional
@RequiredArgsConstructor
public class EnterByCodeGroupServiceImpl implements EnterByCodeGroupService {

    private final GroupInviteRepository groupInviteRepository;
    private final GroupRepository groupRepository;
    private final UserUtil util;
    private final CheckBlackMemberUtil checkBlackMember;

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

            checkBlackMember.check(group, user);

            group.putMember(user, "member");

            groupRepository.save(group);
        } else {
            throw new MisMatchInviteCodeException();
        }
    }
}
