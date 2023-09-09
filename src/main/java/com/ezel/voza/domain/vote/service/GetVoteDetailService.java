package com.ezel.voza.domain.vote.service;

import com.ezel.voza.domain.vote.presentation.dto.response.DetailVoteResponse;

public interface GetVoteDetailService {

    DetailVoteResponse execute(Long id);
}
