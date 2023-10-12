package com.ezel.voza.domain.board.exception;

import com.ezel.voza.global.error.BasicException;
import com.ezel.voza.global.error.ErrorCode;
import lombok.Getter;

@Getter
public class BoardAuthorMismatchException extends BasicException {

    public BoardAuthorMismatchException() {
        super(ErrorCode.MISMATCH_BOARD_AUTHOR);
    }
}
