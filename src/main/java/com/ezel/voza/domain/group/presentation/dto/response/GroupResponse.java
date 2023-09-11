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

    public static GroupResponse groupResponse(Group group) {

        return GroupResponse.builder()
                .groupId(group.getGroupId())
                .groupName(group.getGroupName())
                .build();
    }
}
