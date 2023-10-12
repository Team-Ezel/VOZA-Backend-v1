package com.ezel.voza.domain.user.exception;

import com.ezel.voza.global.error.BasicException;
import com.ezel.voza.global.error.ErrorCode;

public class YouNotAdminException extends BasicException {

    public YouNotAdminException() {
        super(ErrorCode.YOU_NOT_ADMIN);
    }
}
