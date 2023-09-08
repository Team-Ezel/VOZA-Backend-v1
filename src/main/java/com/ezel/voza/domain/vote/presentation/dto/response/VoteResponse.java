package com.ezel.voza.domain.vote.presentation.dto.response;

import com.ezel.voza.domain.vote.entity.Vote;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class VoteResponse {

    private Long id;

    private String title;

    public static VoteResponse toResponse(Vote vote) {

        return VoteResponse.builder()
                .id(vote.getId())
                .title(vote.getTitle())
                .build();
    }
}
