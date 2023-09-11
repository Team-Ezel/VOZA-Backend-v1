package com.ezel.voza.domain.vote.service.impl;

import com.ezel.voza.domain.vote.entity.Vote;
import com.ezel.voza.domain.vote.entity.VoteOption;
import com.ezel.voza.domain.vote.exception.VoteOptionMismatchException;
import com.ezel.voza.domain.vote.presentation.dto.request.AddCountRequest;
import com.ezel.voza.domain.vote.repository.VoteOptionRepository;
import com.ezel.voza.domain.vote.service.AddCountService;
import com.ezel.voza.global.util.VoteUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
@RequiredArgsConstructor
public class AddCountServiceImpl implements AddCountService {

    private final VoteUtil voteUtil;

    private final VoteOptionRepository voteOptionRepository;

    @Override
    public void execute(Long vote_id, AddCountRequest addCountRequest) {

        Vote vote = voteUtil.findVoteById(vote_id);

        VoteOption voteOption = voteOptionRepository.findOneById(addCountRequest.getId());

        if(!(Objects.equals(voteOption.getVote(), vote))) {
            throw new VoteOptionMismatchException();
        }

        voteOption.addCount();
    }
}
