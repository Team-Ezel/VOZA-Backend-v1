package com.ezel.voza.domain.group.service.impl;

import com.ezel.voza.domain.group.entity.Group;
import com.ezel.voza.domain.group.presentation.dto.response.GroupResponse;
import com.ezel.voza.domain.group.service.GroupRecommendedService;
import com.ezel.voza.global.annotation.ReadOnlyService;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.List;

import static com.ezel.voza.domain.group.entity.QGroup.group;

@ReadOnlyService
@RequiredArgsConstructor
public class GroupRecommendedServiceImpl implements GroupRecommendedService {

    private final JPAQueryFactory queryFactory;

    @Override
    public List<GroupResponse> execute() {

        List<Group> groups = queryFactory
                .selectFrom(group)
                .orderBy(group.members.size().desc())
                .limit(5)
                .fetch();

        List<GroupResponse> groupResponses = new ArrayList<>();

        for (Group group : groups) {
            GroupResponse response = mapGroupToResponse(group);
            groupResponses.add(response);
        }

        return groupResponses;
    }

    private GroupResponse mapGroupToResponse(Group group) {

        return GroupResponse.builder()
                .groupId(group.getGroupId())
                .groupName(group.getGroupName())
                .leaderName(group.getLeaderName())
                .members(group.getMembers().size())
                .url(group.getUrl())
                .build();
    }
}
