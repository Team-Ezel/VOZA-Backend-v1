package com.ezel.voza.domain.vote.service.impl;

import com.ezel.voza.domain.group.entity.Group;
import com.ezel.voza.domain.vote.entity.Vote;
import com.ezel.voza.domain.vote.presentation.dto.response.ListVoteResponse;
import com.ezel.voza.domain.vote.repository.VoteRepository;
import com.ezel.voza.domain.vote.service.ListVoteService;
import com.ezel.voza.global.util.GroupUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import static com.ezel.voza.domain.vote.presentation.dto.response.VoteResponse.toResponse;

@Service
@RequiredArgsConstructor
public class ListVoteServiceImpl implements ListVoteService {

    private final VoteRepository voteRepository;

    private final GroupUtil groupUtil;

    @Override
    public ListVoteResponse execute(UUID id){

        Group group = groupUtil.findGroupById(id);

        List<Vote> votes = voteRepository.findAllByGroup(group);

        ListVoteResponse listVoteResponse = ListVoteResponse.builder()
                .voteList(
                        votes.stream()
                                .map(vote -> toResponse(vote))
                                .collect(Collectors.toList())
                )
                .build();

        return listVoteResponse;
    }
}
