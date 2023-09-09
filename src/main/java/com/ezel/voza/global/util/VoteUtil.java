package com.ezel.voza.global.util;

import com.ezel.voza.domain.vote.entity.Vote;
import com.ezel.voza.domain.vote.exception.VoteNotFoundException;
import com.ezel.voza.domain.vote.repository.VoteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class VoteUtil {

    private final VoteRepository voteRepository;

    public Vote findVoteById(Long id) {

        return voteRepository.findById(id)
                .orElseThrow(() -> new VoteNotFoundException());
    }
}
