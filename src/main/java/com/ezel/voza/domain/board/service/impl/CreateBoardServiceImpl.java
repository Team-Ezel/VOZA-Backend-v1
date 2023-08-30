package com.ezel.voza.domain.board.service.impl;

import com.ezel.voza.domain.board.entity.Board;
import com.ezel.voza.domain.board.entity.enums.BoardType;
import com.ezel.voza.domain.board.presentation.dto.request.CreateBoardRequest;
import com.ezel.voza.domain.board.repository.BoardRepository;
import com.ezel.voza.domain.board.service.CreateBoardService;
import com.ezel.voza.domain.member.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class CreateBoardServiceImpl implements CreateBoardService {

    private final User user;

    private final BoardRepository boardRepository;

    public void execute(CreateBoardRequest createBoardRequest) {

        Board board = Board.builder()
                .title(createBoardRequest.getTitle())
                .content(createBoardRequest.getContent())
                .boardType(BoardType.from(createBoardRequest.getBoardType()))
                .author(user.getNickName())
                .createdDate(LocalDateTime.now())
                .editedDate(LocalDateTime.now())
                .build();

        boardRepository.save(board);
    }
}
