package com.ezel.voza.domain.group.service.impl;

import com.ezel.voza.domain.group.entity.Group;
import com.ezel.voza.domain.group.exception.YouNotLeaderException;
import com.ezel.voza.domain.group.repository.GroupRepository;
import com.ezel.voza.domain.group.service.SetGroupSecurityService;
import com.ezel.voza.domain.user.entity.User;
import com.ezel.voza.global.util.GroupUtil;
import com.ezel.voza.global.util.UserUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
@RequiredArgsConstructor
public class SetGroupSecurityServiceImpl implements SetGroupSecurityService {

    private final GroupUtil groupUtil;
    private final UserUtil userUtil;
    private final GroupRepository groupRepository;

    @Override
    public void execute(Boolean canEnter, Long groupId) {

        Group group = groupUtil.findGroupById(groupId);

        User user = userUtil.currentUser();

        if (!Objects.equals(group.getMembers().get(user), "Leader")) {
            throw new YouNotLeaderException();
        }

        group.setSecurity(canEnter);

        groupRepository.save(group);
    }
}
