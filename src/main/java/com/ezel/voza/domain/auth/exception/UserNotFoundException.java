package com.ezel.voza.domain.auth.exception;

import com.ezel.voza.global.error.BasicException;
import com.ezel.voza.global.error.ErrorCode;

public class UserNotFoundException extends BasicException {

    public UserNotFoundException() {
        super(ErrorCode.USER_NOT_FOUND);
    }
}
