package com.ezel.voza.domain.vote.presentation.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
@AllArgsConstructor
public class ListVoteResponse {

    private List<VoteResponse> voteList;
}
