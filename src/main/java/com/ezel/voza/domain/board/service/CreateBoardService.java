package com.ezel.voza.domain.board.service;

import com.ezel.voza.domain.board.presentation.dto.request.CreateBoardRequest;

public interface CreateBoardService {

    void execute(CreateBoardRequest createBoardRequest, Long groupId);
}
