package com.ezel.voza.domain.vote.exception;

import com.ezel.voza.global.error.BasicException;
import com.ezel.voza.global.error.ErrorCode;
import lombok.Getter;

@Getter
public class VoteAuthorMismatchException extends BasicException {

    public VoteAuthorMismatchException() {
        super(ErrorCode.MISMATCH_VOTE_AUTHOR);
    }
}
