package com.ezel.voza.domain.group.exception;

import com.ezel.voza.global.error.BasicException;
import com.ezel.voza.global.error.ErrorCode;

public class KickOutUserException extends BasicException {

    public KickOutUserException() {
        super(ErrorCode.KICK_OUT_USER);
    }
}
