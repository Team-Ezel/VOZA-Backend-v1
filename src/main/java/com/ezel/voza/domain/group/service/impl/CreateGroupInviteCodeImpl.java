package com.ezel.voza.domain.group.service.impl;

import com.ezel.voza.domain.group.entity.Group;
import com.ezel.voza.domain.group.entity.GroupInvite;
import com.ezel.voza.domain.group.exception.GroupNotFoundException;
import com.ezel.voza.domain.group.exception.NotGroupLeaderException;
import com.ezel.voza.domain.group.repository.GroupInviteRepository;
import com.ezel.voza.domain.group.repository.GroupRepository;
import com.ezel.voza.domain.group.service.CreateGroupInviteCode;
import com.ezel.voza.domain.user.entity.User;
import com.ezel.voza.global.annotation.ServiceWithTransactional;
import com.ezel.voza.global.util.UserUtil;
import lombok.RequiredArgsConstructor;

import java.util.Objects;
import java.util.Random;

@ServiceWithTransactional
@RequiredArgsConstructor
public class CreateGroupInviteCodeImpl implements CreateGroupInviteCode {

    private final GroupRepository groupRepository;
    private final GroupInviteRepository groupInviteRepository;
    private final UserUtil util;
    private static final long expiresAt = 60 * 60;

    @Override
    public String generateInviteCode(Long groupId) {

        Group group = groupRepository.findById(groupId)
                .orElseThrow(GroupNotFoundException::new);

        User user = util.currentUser();

        if(!Objects.equals(group.getLeaderName(), user.getNickName())) {
            throw new NotGroupLeaderException();
        }

        Random random = new Random();
        Integer randomCode = random.nextInt(1000000);

        String inviteCode = String.format("%06d", randomCode);

        GroupInvite groupInvite = new GroupInvite(user.getEmail(), inviteCode, groupId, expiresAt);
        groupInviteRepository.save(groupInvite);

        return inviteCode;
    }
}
