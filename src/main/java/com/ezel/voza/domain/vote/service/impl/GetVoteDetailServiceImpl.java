package com.ezel.voza.domain.vote.service.impl;

import com.ezel.voza.domain.user.entity.User;
import com.ezel.voza.domain.vote.entity.Vote;
import com.ezel.voza.domain.vote.entity.VoteOption;
import com.ezel.voza.domain.vote.presentation.dto.response.DetailVoteResponse;
import com.ezel.voza.domain.vote.presentation.dto.response.VoteOptionResponse;
import com.ezel.voza.domain.vote.service.GetVoteDetailService;
import com.ezel.voza.global.annotation.ReadOnlyService;
import com.ezel.voza.global.util.VoteUtil;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@ReadOnlyService
@RequiredArgsConstructor
public class GetVoteDetailServiceImpl implements GetVoteDetailService {

    private final VoteUtil voteUtil;

    @Override
    public DetailVoteResponse execute(Long id) {

        Vote vote = voteUtil.findVoteById(id);

        User user = vote.getUser();

        List<VoteOption> voteOptions = vote.getVoteOptions();

        return DetailVoteResponse.builder()
                .id(vote.getId())
                .title(vote.getTitle())
                .author(user.getNickName())
                .createdDate(vote.getCreatedDate())
                .voteOptions(
                        voteOptions.stream()
                                .map(VoteOptionResponse::toResponse)
                                .collect(Collectors.toList())
                )
                .build();
    }
}
