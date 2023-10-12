package com.ezel.voza.domain.group.exception;

import com.ezel.voza.global.error.BasicException;
import com.ezel.voza.global.error.ErrorCode;

public class MisMatchInviteCodeException extends BasicException {

    public MisMatchInviteCodeException() {
        super(ErrorCode.MISMATCH_INVITE_CODE);
    }
}
