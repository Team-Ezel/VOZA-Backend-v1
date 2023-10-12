package com.ezel.voza.domain.group.exception;

import com.ezel.voza.global.error.BasicException;
import com.ezel.voza.global.error.ErrorCode;

public class NotGroupLeaderException extends BasicException {

    public NotGroupLeaderException() {
        super(ErrorCode.NOT_LEADER);
    }
}
