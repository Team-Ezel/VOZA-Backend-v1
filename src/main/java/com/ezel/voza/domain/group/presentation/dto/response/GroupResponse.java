package com.ezel.voza.domain.group.presentation.dto.response;

import com.ezel.voza.domain.group.entity.Group;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class GroupResponse {

    private Long groupId;

    private String groupName;

    private String url;

    private String leaderName;

    private Integer members;

    public static GroupResponse groupResponse(Group group) {

        return GroupResponse.builder()
                .groupId(group.getGroupId())
                .groupName(group.getGroupName())
                .url(group.getUrl())
                .leaderName(group.getLeaderName())
                .members(group.getMembers().size())
                .build();
    }
}
