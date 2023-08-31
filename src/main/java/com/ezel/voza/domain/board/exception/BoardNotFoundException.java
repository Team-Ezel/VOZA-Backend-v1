package com.ezel.voza.domain.board.exception;

import com.ezel.voza.global.error.BasicException;
import com.ezel.voza.global.error.ErrorCode;
import lombok.Getter;

@Getter
public class BoardNotFoundException extends BasicException {

    public BoardNotFoundException() {
        super(ErrorCode.BOARD_NOT_FOUND);
    }
}
