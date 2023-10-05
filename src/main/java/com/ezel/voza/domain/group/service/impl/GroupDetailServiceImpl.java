package com.ezel.voza.domain.group.service.impl;

import com.ezel.voza.domain.group.entity.Group;
import com.ezel.voza.domain.group.presentation.dto.response.GroupDetailResponse;
import com.ezel.voza.domain.group.service.GroupDetailService;
import com.ezel.voza.global.annotation.ReadOnlyService;
import com.ezel.voza.global.util.GroupUtil;
import com.ezel.voza.global.util.UserUtil;
import lombok.RequiredArgsConstructor;

@ReadOnlyService
@RequiredArgsConstructor
public class GroupDetailServiceImpl implements GroupDetailService {

    private final GroupUtil groupUtil;
    private final UserUtil userUtil;

    @Override
    public GroupDetailResponse execute(Long groupId) {

        Group group = groupUtil.findGroupById(groupId);

        return GroupDetailResponse.builder()
                .groupName(group.getGroupName())
                .url(group.getUrl())
                .introduce(group.getIntroduceGroup())
                .members(group.getMembers().size())
                .createTime(group.getCreatedDate())
                .leaderName(group.getLeaderName())
                .tags(group.getTags()).build();
    }
}
