package com.ezel.voza.domain.vote.exception;

import com.ezel.voza.global.error.BasicException;
import com.ezel.voza.global.error.ErrorCode;
import lombok.Getter;

@Getter
public class VoteOptionMismatchException extends BasicException {

    public VoteOptionMismatchException() {
        super(ErrorCode.MISMATCH_VOTE_OPTION);
    }
}
