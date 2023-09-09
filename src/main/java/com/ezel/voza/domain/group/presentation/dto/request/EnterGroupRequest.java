package com.ezel.voza.domain.group.presentation.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class EnterGroupRequest {

    @NotBlank(message = "초대한 사람의 이메일은 필수입니다.")
    private String email;

    @NotBlank(message = "초대코드는 필수입니다.")
    private String inviteCode;
}
