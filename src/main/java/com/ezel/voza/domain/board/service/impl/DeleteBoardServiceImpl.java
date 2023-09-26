package com.ezel.voza.domain.board.service.impl;

import com.ezel.voza.domain.board.entity.Board;
import com.ezel.voza.domain.board.exception.BoardAuthorMismatchException;
import com.ezel.voza.domain.board.repository.BoardRepository;
import com.ezel.voza.domain.board.service.DeleteBoardService;
import com.ezel.voza.domain.user.entity.User;
import com.ezel.voza.global.util.BoardUtil;
import com.ezel.voza.global.util.UserUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
@RequiredArgsConstructor
public class DeleteBoardServiceImpl implements DeleteBoardService {

    private final BoardRepository boardRepository;

    private final BoardUtil boardUtil;

    private final UserUtil userUtil;

    @Override
    public void execute(Long boardId) {

        Board board = boardUtil.findBoardById(boardId);

        User user = userUtil.currentUser();

        if(!(Objects.equals(board.getAuthor(), user.getNickName()))) {
            throw new BoardAuthorMismatchException();
        }

        boardRepository.delete(board);
    }
}
