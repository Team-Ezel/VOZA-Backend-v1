package com.ezel.voza.domain.group.presentation.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class CreateGroupRequest {

    @NotBlank(message = "GroupName은 필수 요소 입니다.")
    private String groupName;

    @NotBlank(message = "그룹소개는 필수 요소입니다.")
    private String introduceGroup;

    @NotBlank(message = "지역은 필수 요소입니다.")
    private String region;

    @NotBlank(message = "태그는 최소 한개이상 입력해야합니다.")
    private List<String> tags;
}
