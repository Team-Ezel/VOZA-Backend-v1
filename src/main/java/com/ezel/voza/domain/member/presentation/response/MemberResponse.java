package com.ezel.voza.domain.member.presentation.response;

import com.ezel.voza.domain.user.entity.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.Map;

@Getter
@Builder
@AllArgsConstructor
public class MemberResponse {

    private Long id;

    private String name;

    private String role;

    private String profileUrl;

    public static MemberResponse toResponse(Map.Entry<User, String> entry) {
        User user = entry.getKey();
        String role = entry.getValue();

        return MemberResponse.builder()
                .id(user.getId())
                .name(user.getNickName())
                .role(role)
                .profileUrl(user.getProfileUrl())
                .build();
    }
}

