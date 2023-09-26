package com.ezel.voza.domain.board.service;

import com.ezel.voza.domain.board.presentation.dto.response.DetailBoardResponse;

public interface GetBoardDetailService {

    DetailBoardResponse execute(Long boardId);
}
