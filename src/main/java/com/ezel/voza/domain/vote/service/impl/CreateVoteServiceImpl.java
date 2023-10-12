package com.ezel.voza.domain.vote.service.impl;

import com.ezel.voza.domain.group.entity.Group;
import com.ezel.voza.domain.user.entity.User;
import com.ezel.voza.domain.vote.entity.Vote;
import com.ezel.voza.domain.vote.entity.VoteOption;
import com.ezel.voza.domain.vote.presentation.dto.request.CreateVoteRequest;
import com.ezel.voza.domain.vote.repository.VoteRepository;
import com.ezel.voza.domain.vote.service.CreateVoteService;
import com.ezel.voza.global.annotation.ServiceWithTransactional;
import com.ezel.voza.global.util.GroupUtil;
import com.ezel.voza.global.util.UserUtil;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

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
                .user(user)
                .group(group)
                .build();

        List<VoteOption> voteOptions = createVoteRequest.getOptions().stream()
                .map(option -> VoteOption.builder()
                        .option(option)
                        .vote(vote)
                        .build())
                .collect(Collectors.toList());

        vote.setVoteOptions(voteOptions);

        voteRepository.save(vote);
    }
}
