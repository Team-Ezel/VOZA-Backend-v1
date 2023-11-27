package com.ezel.voza.domain.member.presentation.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
@AllArgsConstructor
public class MemberListResponse {

    private boolean leaderCheck;

    private List<MemberResponse> memberResponses;
}
