package com.ezel.voza.global.util;

import com.ezel.voza.domain.board.entity.Board;
import com.ezel.voza.domain.board.exception.BoardNotFoundException;
import com.ezel.voza.domain.board.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class BoardUtil {

    private final BoardRepository boardRepository;

    public Board findBoardById(Long id) {

        return boardRepository.findById(id)
                .orElseThrow(() -> new BoardNotFoundException());
    }
}
