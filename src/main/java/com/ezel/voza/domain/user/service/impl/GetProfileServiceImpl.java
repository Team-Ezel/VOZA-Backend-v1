package com.ezel.voza.domain.user.service.impl;

import com.ezel.voza.domain.group.entity.Group;
import com.ezel.voza.domain.group.repository.GroupRepository;
import com.ezel.voza.domain.user.entity.User;
import com.ezel.voza.domain.user.presentation.dto.response.JoinedGroupResponse;
import com.ezel.voza.domain.user.presentation.dto.response.ProfileResponse;
import com.ezel.voza.domain.user.service.GetProfileService;
import com.ezel.voza.global.annotation.ReadOnlyService;
import com.ezel.voza.global.util.UserUtil;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@ReadOnlyService
@RequiredArgsConstructor
public class GetProfileServiceImpl implements GetProfileService {

    private final UserUtil userUtil;

    private final GroupRepository groupRepository;

    @Override
    public ProfileResponse execute() {
        User user = userUtil.currentUser();

        List<Group> groupList = groupRepository.findGroupsByMembers(user);

        return ProfileResponse.builder()
                .email(user.getEmail())
                .profileUrl(user.getProfileUrl())
                .nickName(user.getNickName())
                .joinedGroupList(
                        groupList.stream()
                                .map(JoinedGroupResponse::toResponse)
                                .collect(Collectors.toList())
                )
                .build();
    }
}
