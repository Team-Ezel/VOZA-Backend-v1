package com.ezel.voza.domain.board.service;

import com.ezel.voza.domain.board.presentation.dto.request.UpdateBoardRequest;

public interface UpdateBoardService {

    void execute(Long boardId, UpdateBoardRequest updateBoardRequest);
}
