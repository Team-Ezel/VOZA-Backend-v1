package com.ezel.voza.domain.board.service.impl;

import com.ezel.voza.domain.board.entity.Board;
import com.ezel.voza.domain.board.presentation.dto.response.ListBoardResponse;
import com.ezel.voza.domain.board.repository.BoardRepository;
import com.ezel.voza.domain.board.service.ListBoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static com.ezel.voza.domain.board.presentation.dto.response.BoardResponse.toResponse;

@Service
@RequiredArgsConstructor
public class ListBoardServiceImpl implements ListBoardService {

    private final BoardRepository boardRepository;

    @Override
    public ListBoardResponse execute() {

        List<Board> boards = boardRepository.findAll();

        ListBoardResponse listBoardResponse = ListBoardResponse.builder()
                .boardList(
                        boards.stream()
                                .map(board -> toResponse(board))
                                .collect(Collectors.toList())
                )
                .build();

        return listBoardResponse;
    }
}
