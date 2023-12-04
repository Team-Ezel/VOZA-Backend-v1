package com.ezel.voza.domain.member.service.impl;

import com.ezel.voza.domain.group.entity.Group;
import com.ezel.voza.domain.member.presentation.response.MemberListResponse;
import com.ezel.voza.domain.member.presentation.response.MemberResponse;
import com.ezel.voza.domain.member.service.MemberListService;
import com.ezel.voza.domain.user.entity.User;
import com.ezel.voza.global.annotation.ReadOnlyService;
import com.ezel.voza.global.util.GroupUtil;
import com.ezel.voza.global.util.UserUtil;
import lombok.RequiredArgsConstructor;

import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

@ReadOnlyService
@RequiredArgsConstructor
public class MemberListServiceImpl implements MemberListService {

    private final GroupUtil groupUtil;

    private final UserUtil userUtil;

    @Override
    public MemberListResponse execute(Long id) {

        Group group = groupUtil.findGroupById(id);

        Map<User, String> members = group.getMembers();

        User currentUser = userUtil.currentUser();

        boolean check = Objects.equals(group.getLeaderName(), currentUser.getNickName());

        return MemberListResponse.builder()
                .leaderCheck(check)
                .memberResponses(
                        members.entrySet().stream()
                                .map(MemberResponse::toResponse)
                                .collect(Collectors.toList())
                )
                .build();
    }
}
