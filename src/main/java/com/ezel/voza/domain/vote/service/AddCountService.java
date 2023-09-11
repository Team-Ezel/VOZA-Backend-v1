package com.ezel.voza.domain.vote.service;

import com.ezel.voza.domain.vote.presentation.dto.request.AddCountRequest;

public interface AddCountService {

    void execute(Long vote_id, AddCountRequest addCountRequest);
}
