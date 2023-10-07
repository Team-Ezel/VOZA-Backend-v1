package com.ezel.voza.domain.vote.presentation.dto.response;

import com.fasterxml.jackson.annotation.JsonFormat;
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

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH-mm-ss")
    private LocalDateTime createdDate;

    private List<VoteOptionResponse> voteOptions;
}
