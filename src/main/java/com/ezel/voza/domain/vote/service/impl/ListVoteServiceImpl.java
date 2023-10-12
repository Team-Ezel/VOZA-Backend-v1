package com.ezel.voza.domain.vote.service.impl;

import com.ezel.voza.domain.group.entity.Group;
import com.ezel.voza.domain.vote.entity.Vote;
import com.ezel.voza.domain.vote.presentation.dto.response.ListVoteResponse;
import com.ezel.voza.domain.vote.presentation.dto.response.VoteResponse;
import com.ezel.voza.domain.vote.repository.VoteRepository;
import com.ezel.voza.domain.vote.service.ListVoteService;
import com.ezel.voza.global.annotation.ReadOnlyService;
import com.ezel.voza.global.util.GroupUtil;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@ReadOnlyService
@RequiredArgsConstructor
public class ListVoteServiceImpl implements ListVoteService {

    private final VoteRepository voteRepository;

    private final GroupUtil groupUtil;

    @Override
    public ListVoteResponse execute(Long id){

        Group group = groupUtil.findGroupById(id);

        List<Vote> votes = voteRepository.findAllByGroup(group);

        return ListVoteResponse.builder()
                .voteList(
                        votes.stream()
                                .map(VoteResponse::toResponse)
                                .collect(Collectors.toList())
                )
                .build();
    }
}
