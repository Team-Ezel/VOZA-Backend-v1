package com.ezel.voza.domain.user.presentation.dto.response;

import com.ezel.voza.domain.group.entity.Group;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class JoinedGroupResponse {

    private String groupName;

    private String groupFileUrl;

    public static JoinedGroupResponse toResponse(Group group) {

        return JoinedGroupResponse.builder()
                .groupName(group.getGroupName())
                .groupFileUrl(group.getUrl())
                .build();
    }
}
