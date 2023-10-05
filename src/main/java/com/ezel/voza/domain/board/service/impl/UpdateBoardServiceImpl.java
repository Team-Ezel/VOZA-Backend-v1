package com.ezel.voza.domain.board.service.impl;

import com.ezel.voza.domain.board.entity.Board;
import com.ezel.voza.domain.board.exception.BoardAuthorMismatchException;
import com.ezel.voza.domain.board.presentation.dto.request.UpdateBoardRequest;
import com.ezel.voza.domain.board.repository.BoardRepository;
import com.ezel.voza.domain.board.service.UpdateBoardService;
import com.ezel.voza.domain.user.entity.User;
import com.ezel.voza.global.annotation.ServiceWithTransactional;
import com.ezel.voza.global.util.BoardUtil;
import com.ezel.voza.global.util.UserUtil;
import lombok.RequiredArgsConstructor;

@ServiceWithTransactional
@RequiredArgsConstructor
public class UpdateBoardServiceImpl implements UpdateBoardService {

    private final BoardUtil boardUtil;

    private final UserUtil userUtil;

    private final BoardRepository boardRepository;

    @Override
    public void execute(Long boardId, UpdateBoardRequest updateBoardRequest) {

        Board board = boardUtil.findBoardById(boardId);

        User user = userUtil.currentUser();

        if (!board.getAuthor().equals(user.getNickName())) {
            throw new BoardAuthorMismatchException();
        }

        board.update(updateBoardRequest);

        boardRepository.save(board);
    }
}
