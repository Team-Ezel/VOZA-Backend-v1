package com.ezel.voza.domain.member.service.impl;

import com.ezel.voza.domain.group.entity.Group;
import com.ezel.voza.domain.group.exception.NotExistGroupException;
import com.ezel.voza.domain.member.exception.NotManagerException;
import com.ezel.voza.domain.member.service.KickOutMemberService;
import com.ezel.voza.domain.user.entity.User;
import com.ezel.voza.global.util.GroupUtil;
import com.ezel.voza.global.util.UserUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Objects;


@Service
@RequiredArgsConstructor
public class KickOutMemberServiceImpl implements KickOutMemberService {

    private final GroupUtil groupUtil;

    private final UserUtil userUtil;

    @Override
    public void execute(Long groupId, Long userId) {

        Group group = groupUtil.findGroupById(groupId);

        User currentUser = userUtil.currentUser();

        if(!Objects.equals(group.getMembers().get(currentUser), "Leader")) {
            throw new NotManagerException();
        }

        User kickOutMember = userUtil.findUserById(userId);

        if (!group.getMembers().containsKey(kickOutMember)) {
            throw new NotExistGroupException();
        }

        group.deleteMember(kickOutMember);
    }
}
