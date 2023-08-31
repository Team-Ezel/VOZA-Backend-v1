package com.ezel.voza.domain.board.service.impl;

import com.ezel.voza.domain.board.entity.Board;
import com.ezel.voza.domain.board.entity.enums.BoardType;
import com.ezel.voza.domain.board.presentation.dto.request.CreateBoardRequest;
import com.ezel.voza.domain.board.repository.BoardRepository;
import com.ezel.voza.domain.board.service.CreateBoardService;
import com.ezel.voza.domain.user.entity.User;
import com.ezel.voza.global.util.UserUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class CreateBoardServiceImpl implements CreateBoardService {

    private final UserUtil util;
    private final BoardRepository boardRepository;

    @Override
    public void execute(CreateBoardRequest createBoardRequest) {

        User user = util.currentUser();

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
