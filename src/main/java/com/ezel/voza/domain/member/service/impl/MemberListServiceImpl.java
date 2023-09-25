package com.ezel.voza.domain.member.service.impl;

import com.ezel.voza.domain.group.entity.Group;
import com.ezel.voza.domain.member.presentation.response.MemberListResponse;
import com.ezel.voza.domain.member.presentation.response.MemberResponse;
import com.ezel.voza.domain.member.service.MemberListService;
import com.ezel.voza.domain.user.entity.User;
import com.ezel.voza.global.util.GroupUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MemberListServiceImpl implements MemberListService {

    private final GroupUtil groupUtil;

    @Override
    public MemberListResponse execute(Long id) {

        Group group = groupUtil.findGroupById(id);

        Map<User, String> members = group.getMembers();

        return MemberListResponse.builder()
                .memberResponses(
                        members.entrySet().stream()
                                .map(MemberResponse::toResponse)
                                .collect(Collectors.toList())
                )
                .build();
    }
}
