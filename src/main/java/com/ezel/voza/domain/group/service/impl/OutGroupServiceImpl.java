package com.ezel.voza.domain.group.service.impl;

import com.ezel.voza.domain.chat.service.RoomOutService;
import com.ezel.voza.domain.group.entity.Group;
import com.ezel.voza.domain.group.exception.LeaderNotOutGroupException;
import com.ezel.voza.domain.group.exception.NotExistGroupException;
import com.ezel.voza.domain.group.repository.GroupRepository;
import com.ezel.voza.domain.group.service.OutGroupService;
import com.ezel.voza.domain.user.entity.User;
import com.ezel.voza.global.util.GroupUtil;
import com.ezel.voza.global.util.UserUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OutGroupServiceImpl implements OutGroupService {

    private final UserUtil userUtil;
    private final GroupUtil groupUtil;
    private final GroupRepository groupRepository;
    private final RoomOutService roomOutService;

    @Override
    public void execute(Long groupId) {
        User user = userUtil.currentUser();

        Group group = groupUtil.findGroupById(groupId);

        if (!group.getMembers().containsKey(user)) {
            throw new NotExistGroupException();
        }

        String userRole = group.getMembers().get(user);

        if ("member".equals(userRole)) {
            group.deleteMember(user);
            groupRepository.save(group);
        } else {
            throw new LeaderNotOutGroupException();
        }

        roomOutService.execute(group);
    }
}
