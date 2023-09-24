package com.ezel.voza.domain.member.presentation.response;

import com.ezel.voza.domain.user.entity.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class MemberResponse {

    private Long id;

    private String name;

    private String role;

    private String profileUrl;

    public static MemberResponse toResponse(User user, String role) {

        return MemberResponse.builder()
                .id(user.getId())
                .name(user.getNickName())
                .role(role)
                .profileUrl(user.getProfileUrl())
                .build();
    }
}

