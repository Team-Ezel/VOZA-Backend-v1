package com.ezel.voza.domain.vote.service;

import com.ezel.voza.domain.vote.presentation.dto.response.ListVoteResponse;

import java.util.UUID;

public interface ListVoteService {

    ListVoteResponse execute(UUID id);
}
