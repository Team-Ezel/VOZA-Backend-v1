package com.ezel.voza.domain.vote.presentation.dto.response;

import com.ezel.voza.domain.vote.entity.VoteOption;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class VoteOptionResponse {

    private long id;

    private String option;

    private int count;

    public static VoteOptionResponse toResponse(VoteOption voteOption) {

        return VoteOptionResponse.builder()
                .id(voteOption.getId())
                .option(voteOption.getOption())
                .count(voteOption.getCount())
                .build();
    }
}
