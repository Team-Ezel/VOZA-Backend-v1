package com.ezel.voza.domain.vote.service;

import com.ezel.voza.domain.vote.presentation.dto.request.CreateVoteRequest;

public interface CreateVoteService {

    void execute(CreateVoteRequest createVoteRequest);
}
