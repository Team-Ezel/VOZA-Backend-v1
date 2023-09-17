package com.ezel.voza.domain.user.presentation.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class ProfileResponse {

    private String email;
    private String profileUrl;
    private String nickName;
}
