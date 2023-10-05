package com.ezel.voza.domain.vote.service.impl;

import com.ezel.voza.domain.group.entity.Group;
import com.ezel.voza.domain.user.entity.User;
import com.ezel.voza.domain.vote.entity.Vote;
import com.ezel.voza.domain.vote.presentation.dto.request.CreateVoteRequest;
import com.ezel.voza.domain.vote.repository.VoteRepository;
import com.ezel.voza.domain.vote.service.CreateVoteService;
import com.ezel.voza.global.annotation.ServiceWithTransactional;
import com.ezel.voza.global.util.GroupUtil;
import com.ezel.voza.global.util.UserUtil;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;

@ServiceWithTransactional
@RequiredArgsConstructor
public class CreateVoteServiceImpl implements CreateVoteService {

    private final VoteRepository voteRepository;

    private final UserUtil userUtil;

    private final GroupUtil groupUtil;

    @Override
    public void execute(CreateVoteRequest createVoteRequest, Long id) {

        User user = userUtil.currentUser();

        Group group = groupUtil.findGroupById(id);

        Vote vote = Vote.builder()
                .title(createVoteRequest.getTitle())
                .voteOptions(createVoteRequest.getOptions())
                .user(user)
                .createdDate(LocalDateTime.now())
                .group(group)
                .build();

        voteRepository.save(vote);
    }
}
