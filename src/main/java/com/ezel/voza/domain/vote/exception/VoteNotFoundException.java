package com.ezel.voza.domain.vote.exception;

import com.ezel.voza.global.error.BasicException;
import com.ezel.voza.global.error.ErrorCode;
import lombok.Getter;

@Getter
public class VoteNotFoundException extends BasicException {

    public VoteNotFoundException() {
        super(ErrorCode.VOTE_NOT_FOUND);
    }
}
