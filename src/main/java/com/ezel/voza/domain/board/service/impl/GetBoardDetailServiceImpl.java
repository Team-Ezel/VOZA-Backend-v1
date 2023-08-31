package com.ezel.voza.domain.board.service.impl;

import com.ezel.voza.domain.board.entity.Board;
import com.ezel.voza.domain.board.presentation.dto.response.DetailBoardResponse;
import com.ezel.voza.global.util.BoardUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GetBoardDetailServiceImpl {

    private final BoardUtil boardUtil;

    public DetailBoardResponse execute(Long id) {

        Board board = boardUtil.findBoardById(id);

        DetailBoardResponse detailBoardResponse = DetailBoardResponse.builder()
                .id(board.getId())
                .title(board.getTitle())
                .content(board.getContent())
                .author(board.getAuthor())
                .createdDate(board.getCreatedDate())
                .editedDate(board.getEditedDate())
                .build();

        return detailBoardResponse;
    }
}
