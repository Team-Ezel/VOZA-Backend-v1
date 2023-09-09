package com.ezel.voza.domain.group.service.impl;

import com.ezel.voza.domain.group.entity.Group;
import com.ezel.voza.domain.group.entity.GroupInvite;
import com.ezel.voza.domain.group.exception.AlreadyExistGroupException;
import com.ezel.voza.domain.group.exception.GroupNotFoundException;
import com.ezel.voza.domain.group.exception.MisMatchInviteCodeException;
import com.ezel.voza.domain.group.presentation.dto.request.EnterGroupRequest;
import com.ezel.voza.domain.group.repository.GroupInviteRepository;
import com.ezel.voza.domain.group.repository.GroupRepository;
import com.ezel.voza.domain.group.service.EnterGroupService;
import com.ezel.voza.domain.user.entity.User;
import com.ezel.voza.global.util.UserUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
@RequiredArgsConstructor
public class EnterGroupServiceImpl implements EnterGroupService {

    private final GroupInviteRepository groupInviteRepository;
    private final GroupRepository groupRepository;
    private final UserUtil util;

    @Override
    public void execute(EnterGroupRequest enterGroupRequest) {

        User user = util.currentUser();


        GroupInvite invite = groupInviteRepository.findById(enterGroupRequest.getEmail())
                .orElseThrow(GroupNotFoundException::new);

        if (Objects.equals(invite.getInviteCode(), enterGroupRequest.getInviteCode())) {

            Group group = groupRepository.findById(invite.getGroupId())
                    .orElseThrow(GroupNotFoundException::new);

            if (group.getMembers().containsKey(user)) {
                throw new AlreadyExistGroupException();
            }

            group.putMember(user, "member");

            groupRepository.save(group);
        } else {
            throw new MisMatchInviteCodeException();
        }
    }
}
