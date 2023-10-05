package com.ezel.voza.domain.member.service.impl;

import com.ezel.voza.domain.group.entity.Group;
import com.ezel.voza.domain.group.exception.NotExistGroupException;
import com.ezel.voza.domain.member.exception.NotManagerException;
import com.ezel.voza.domain.member.service.GrantLeaderRoleService;
import com.ezel.voza.domain.user.entity.User;
import com.ezel.voza.global.annotation.ServiceWithTransactional;
import com.ezel.voza.global.util.GroupUtil;
import com.ezel.voza.global.util.UserUtil;
import lombok.RequiredArgsConstructor;

import java.util.Objects;

@ServiceWithTransactional
@RequiredArgsConstructor
public class GrantLeaderRoleServiceImpl implements GrantLeaderRoleService {

    private final GroupUtil groupUtil;

    private final UserUtil userUtil;

    @Override
    public void execute(Long groupId, Long userId) {

        Group group = groupUtil.findGroupById(groupId);

        User currentUser = userUtil.currentUser();

        if(!Objects.equals(group.getLeaderName(), currentUser.getNickName())) {
            throw new NotManagerException();
        }

        User user = userUtil.findUserById(userId);

        if (!group.getMembers().containsKey(user)) {
            throw new NotExistGroupException();
        }

        group.putMember(currentUser, "member");
        group.putMember(user, "Leader");
        group.updateLeaderName(user.getNickName());
    }
}
