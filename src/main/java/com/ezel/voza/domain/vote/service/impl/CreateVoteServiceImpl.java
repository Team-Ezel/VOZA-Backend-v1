package com.ezel.voza.domain.vote.service.impl;

import com.ezel.voza.domain.user.entity.User;
import com.ezel.voza.domain.vote.entity.Vote;
import com.ezel.voza.domain.vote.presentation.dto.request.CreateVoteRequest;
import com.ezel.voza.domain.vote.repository.VoteRepository;
import com.ezel.voza.domain.vote.service.CreateVoteService;
import com.ezel.voza.global.util.UserUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class CreateVoteServiceImpl implements CreateVoteService {

    private final VoteRepository voteRepository;

    private final UserUtil userUtil;

    @Override
    public void execute(CreateVoteRequest createVoteRequest) {

        User user = userUtil.currentUser();

        Vote vote = Vote.builder()
                .title(createVoteRequest.getTitle())
                .voteOptions(createVoteRequest.getOptions())
                .author(user.getNickName())
                .createdDate(LocalDateTime.now())
                .build();

        voteRepository.save(vote);
    }
}
