package com.ezel.voza.domain.board.service.impl;

import com.ezel.voza.domain.board.entity.Board;
import com.ezel.voza.domain.board.exception.BoardAuthorMismatchException;
import com.ezel.voza.domain.board.presentation.dto.request.UpdateBoardRequest;
import com.ezel.voza.domain.user.entity.User;
import com.ezel.voza.global.util.BoardUtil;
import com.ezel.voza.global.util.UserUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UpdateBoardServiceImpl {

    private final BoardUtil boardUtil;

    private final UserUtil userUtil;

    public void execute(Long id, UpdateBoardRequest updateBoardRequest) {

        Board board = boardUtil.findBoardById(id);

        User user = userUtil.currentUser();

        if (!board.getAuthor().equals(user.getNickName())) {
            throw new BoardAuthorMismatchException();
        }

        board.update(updateBoardRequest);
    }
}
