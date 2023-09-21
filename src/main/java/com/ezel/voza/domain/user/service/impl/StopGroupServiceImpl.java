package com.ezel.voza.domain.user.service.impl;

import com.ezel.voza.domain.group.entity.Group;
import com.ezel.voza.domain.group.exception.GroupNotFoundException;
import com.ezel.voza.domain.group.repository.GroupRepository;
import com.ezel.voza.domain.user.entity.enums.Role;
import com.ezel.voza.domain.user.exception.ExistBanGroupException;
import com.ezel.voza.domain.user.exception.YouNotAdminException;
import com.ezel.voza.domain.user.service.StopGroupService;
import com.ezel.voza.global.util.UserUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class StopGroupServiceImpl implements StopGroupService {

    private final UserUtil userUtil;
    private final GroupRepository groupRepository;

    @Override
    public void execute(Long groupId) {

        if (userUtil.currentUser().getRole() != Role.ROLE_ADMIN) {
            throw new YouNotAdminException();
        }

        Group group = groupRepository.findById(groupId)
                .orElseThrow(GroupNotFoundException::new);

        if (group.getStop()) {
            throw new ExistBanGroupException();
        }

        group.setBan(true);

        groupRepository.save(group);
    }
}
