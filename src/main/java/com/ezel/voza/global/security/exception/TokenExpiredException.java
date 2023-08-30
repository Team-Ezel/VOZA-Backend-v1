package com.ezel.voza.global.security.exception;

import com.ezel.voza.global.error.BasicException;
import com.ezel.voza.global.error.ErrorCode;

public class TokenExpiredException extends BasicException {

    public TokenExpiredException() {
        super(ErrorCode.TOKEN_IS_EXPIRED);
    }
}
