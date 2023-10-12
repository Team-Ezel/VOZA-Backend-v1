package com.ezel.voza.domain.group.presentation.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.util.Set;

@Getter
@Builder
@AllArgsConstructor
public class GroupDetailResponse {

    private String groupName;
    private String url;
    private String introduce;
    private Integer members;
    private LocalDateTime createTime;
    private String leaderName;
    private Set<String> tags;
}
