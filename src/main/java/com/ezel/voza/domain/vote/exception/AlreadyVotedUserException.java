package com.ezel.voza.domain.vote.exception;

import com.ezel.voza.global.error.BasicException;
import com.ezel.voza.global.error.ErrorCode;

public class AlreadyVotedUserException extends BasicException {

    public AlreadyVotedUserException() {
        super(ErrorCode.ALREADY_VOTED_USER);
    }
}
