package com.ezel.voza.domain.group.service.impl;

import com.ezel.voza.domain.group.entity.Group;
import com.ezel.voza.domain.group.exception.AlreadyExistGroupException;
import com.ezel.voza.domain.group.exception.EnterOnlyCodeException;
import com.ezel.voza.domain.group.exception.NotAllowedEnterBanGroupException;
import com.ezel.voza.domain.group.repository.GroupRepository;
import com.ezel.voza.domain.group.service.EnterGroupService;
import com.ezel.voza.domain.user.entity.User;
import com.ezel.voza.global.util.CheckBlackMemberUtil;
import com.ezel.voza.global.util.GroupUtil;
import com.ezel.voza.global.util.UserUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EnterGroupServiceImpl implements EnterGroupService {

    private final GroupUtil groupUtil;
    private final GroupRepository groupRepository;
    private final UserUtil userUtil;
    private final CheckBlackMemberUtil checkBlackMember;

    @Override
    public void execute(Long groupId) {

        Group group = groupUtil.findGroupById(groupId);

        User user = userUtil.currentUser();

        if (group.getMembers().containsKey(user)) {
            throw new AlreadyExistGroupException();
        }

        if(group.getCanEnter()) {
            throw new EnterOnlyCodeException();
        }

        if (group.getStop()) {
            throw new NotAllowedEnterBanGroupException();
        }

        checkBlackMember.check(group, user);

        group.putMember(user, "member");

        groupRepository.save(group);
    }
}
