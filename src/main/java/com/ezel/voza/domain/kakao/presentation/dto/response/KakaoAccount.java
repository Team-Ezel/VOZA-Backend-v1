package com.ezel.voza.domain.kakao.presentation.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class KakaoAccount {
    private Profile profile;
    private String email;
}
