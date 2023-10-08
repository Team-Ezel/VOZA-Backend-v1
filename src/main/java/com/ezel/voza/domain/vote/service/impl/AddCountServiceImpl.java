package com.ezel.voza.domain.vote.service.impl;

import com.ezel.voza.domain.user.entity.User;
import com.ezel.voza.domain.vote.entity.Vote;
import com.ezel.voza.domain.vote.entity.VoteOption;
import com.ezel.voza.domain.vote.exception.AlreadyVotedUserException;
import com.ezel.voza.domain.vote.exception.VoteOptionMismatchException;
import com.ezel.voza.domain.vote.repository.VoteOptionRepository;
import com.ezel.voza.domain.vote.service.AddCountService;
import com.ezel.voza.global.annotation.ServiceWithTransactional;
import com.ezel.voza.global.util.UserUtil;
import com.ezel.voza.global.util.VoteUtil;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Objects;

@ServiceWithTransactional
@RequiredArgsConstructor
public class AddCountServiceImpl implements AddCountService {

    private final VoteUtil voteUtil;

    private final VoteOptionRepository voteOptionRepository;

    private final UserUtil userUtil;

    @Override
    public void execute(Long voteId, Long optionId) {

        Vote vote = voteUtil.findVoteById(voteId);

        VoteOption voteOption = voteOptionRepository.findOneById(optionId);

        if(!(Objects.equals(voteOption.getVote(), vote))) {
            throw new VoteOptionMismatchException();
        }

        List<User> users = vote.getVotedUsers();

        User currentUser = userUtil.currentUser();

        if(users.contains(currentUser)) {
            throw new AlreadyVotedUserException();
        }

        vote.putUser(currentUser);
        voteOption.addCount();

        voteOptionRepository.save(voteOption);
    }
}