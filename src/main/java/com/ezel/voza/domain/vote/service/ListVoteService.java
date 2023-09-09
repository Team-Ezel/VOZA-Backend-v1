package com.ezel.voza.domain.vote.service;

import com.ezel.voza.domain.vote.presentation.dto.response.ListVoteResponse;

public interface ListVoteService {

    ListVoteResponse execute(Long id);
}
