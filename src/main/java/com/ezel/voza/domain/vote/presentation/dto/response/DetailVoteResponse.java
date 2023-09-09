package com.ezel.voza.domain.vote.presentation.dto.response;

import com.ezel.voza.domain.vote.entity.VoteOption;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Builder
@AllArgsConstructor
public class DetailVoteResponse {

    private Long id;

    private String title;

    private String author;

    private LocalDateTime createdDate;

    private List<VoteOption> voteOptions;
}
