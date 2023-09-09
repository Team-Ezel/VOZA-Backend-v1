package com.ezel.voza.domain.group.exception;

import com.ezel.voza.global.error.BasicException;
import com.ezel.voza.global.error.ErrorCode;

public class AlreadyExistGroupException extends BasicException {

    public AlreadyExistGroupException() {
        super(ErrorCode.ALREADY_EXIST_GROUP);
    }

}
