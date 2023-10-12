package com.ezel.voza.domain.kakao.presentation.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class Profile {
    private String profile_image_url;
    private String nickname;
}