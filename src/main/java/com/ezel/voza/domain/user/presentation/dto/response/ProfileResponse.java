package com.ezel.voza.domain.user.presentation.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
@AllArgsConstructor
public class ProfileResponse {

    private String email;
    private String profileUrl;
    private String nickName;
    private List<JoinedGroupResponse> joinedGroupList;
}
