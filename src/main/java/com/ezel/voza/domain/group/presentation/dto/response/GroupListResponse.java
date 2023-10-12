package com.ezel.voza.domain.group.presentation.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
@AllArgsConstructor
public class GroupListResponse {

    List<GroupResponse> groupList;
}
