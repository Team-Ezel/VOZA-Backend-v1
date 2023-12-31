package com.ezel.voza.domain.board.service.impl;

import com.ezel.voza.domain.board.entity.Board;
import com.ezel.voza.domain.board.presentation.dto.response.BoardResponse;
import com.ezel.voza.domain.board.presentation.dto.response.ListBoardResponse;
import com.ezel.voza.domain.board.repository.BoardRepository;
import com.ezel.voza.domain.board.service.ListBoardService;
import com.ezel.voza.domain.group.entity.Group;
import com.ezel.voza.global.annotation.ReadOnlyService;
import com.ezel.voza.global.util.GroupUtil;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@ReadOnlyService
@RequiredArgsConstructor
public class ListBoardServiceImpl implements ListBoardService {

    private final BoardRepository boardRepository;

    private final GroupUtil groupUtil;

    @Override
    public ListBoardResponse execute(Long groupId) {

        Group group = groupUtil.findGroupById(groupId);

        List<Board> boards = boardRepository.findALlByGroup(group);

        return ListBoardResponse.builder()
                .boardList(
                        boards.stream()
                                .map(BoardResponse::toResponse)
                                .collect(Collectors.toList())
                )
                .build();
    }
}
