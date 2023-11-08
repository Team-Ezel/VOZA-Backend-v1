package com.ezel.voza.domain.group.service.impl;

import com.ezel.voza.domain.group.entity.Group;
import com.ezel.voza.domain.group.presentation.dto.response.GroupListResponse;
import com.ezel.voza.domain.group.presentation.dto.response.GroupResponse;
import com.ezel.voza.domain.group.repository.GroupRepository;
import com.ezel.voza.domain.group.service.GroupListService;
import com.ezel.voza.global.annotation.ReadOnlyService;
import com.ezel.voza.global.util.UserUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;

import java.util.List;
import java.util.stream.Collectors;

@ReadOnlyService
@RequiredArgsConstructor
@Qualifier("myGroupListService")
public class MyGroupListServiceImpl implements GroupListService {

    private final GroupRepository groupRepository;
    private final UserUtil util;

    @Override
    public GroupListResponse execute() {

        List<Group> groupList = groupRepository.findGroupsByMember(util.currentUser());

        return GroupListResponse.builder()
                .groupList(
                    groupList.stream()
                            .map(GroupResponse::groupResponse)
                            .collect(Collectors.toList())
                )
                .build();
    }
}
