package com.ezel.voza.domain.vote.presentation.dto.response;

import com.ezel.voza.domain.vote.entity.Vote;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
@AllArgsConstructor
public class VoteResponse {

    private Long id;

    private String title;

    private String author;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH-mm-ss")
    private LocalDateTime createdDate;

    public static VoteResponse toResponse(Vote vote) {

        return VoteResponse.builder()
                .id(vote.getId())
                .title(vote.getTitle())
                .author(vote.getUser().getNickName())
                .createdDate(vote.getCreatedDate())
                .build();
    }
}
