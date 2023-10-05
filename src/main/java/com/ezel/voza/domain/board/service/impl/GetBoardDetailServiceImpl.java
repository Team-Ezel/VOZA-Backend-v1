package com.ezel.voza.domain.board.service.impl;

import com.ezel.voza.domain.board.entity.Board;
import com.ezel.voza.domain.board.presentation.dto.response.DetailBoardResponse;
import com.ezel.voza.domain.board.service.GetBoardDetailService;
import com.ezel.voza.global.annotation.ReadOnlyService;
import com.ezel.voza.global.util.BoardUtil;
import lombok.RequiredArgsConstructor;

@ReadOnlyService
@RequiredArgsConstructor
public class GetBoardDetailServiceImpl implements GetBoardDetailService {

    private final BoardUtil boardUtil;

    @Override
    public DetailBoardResponse execute(Long boardId) {

        Board board = boardUtil.findBoardById(boardId);

        return DetailBoardResponse.builder()
                .boardId(board.getId())
                .title(board.getTitle())
                .content(board.getContent())
                .author(board.getAuthor())
                .createdDate(board.getCreatedDate())
                .editedDate(board.getModifiedDate())
                .build();
    }
}
