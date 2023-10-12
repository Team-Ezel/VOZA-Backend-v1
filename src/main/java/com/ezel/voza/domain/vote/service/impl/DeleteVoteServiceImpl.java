package com.ezel.voza.domain.vote.service.impl;

import com.ezel.voza.domain.user.entity.User;
import com.ezel.voza.domain.vote.entity.Vote;
import com.ezel.voza.domain.vote.exception.VoteAuthorMismatchException;
import com.ezel.voza.domain.vote.repository.VoteRepository;
import com.ezel.voza.domain.vote.service.DeleteVoteService;
import com.ezel.voza.global.annotation.ServiceWithTransactional;
import com.ezel.voza.global.util.UserUtil;
import com.ezel.voza.global.util.VoteUtil;
import lombok.RequiredArgsConstructor;

import java.util.Objects;

@ServiceWithTransactional
@RequiredArgsConstructor
public class DeleteVoteServiceImpl implements DeleteVoteService {

    private final VoteRepository voteRepository;

    private final VoteUtil voteUtil;

    private final UserUtil userUtil;

    @Override
    public void execute(Long id) {

        Vote vote = voteUtil.findVoteById(id);

        User user = userUtil.currentUser();

        if(!(Objects.equals(vote.getUser(), user))) {
            throw new VoteAuthorMismatchException();
        }

        voteRepository.delete(vote);
    }
}
