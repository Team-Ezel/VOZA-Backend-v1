package com.ezel.voza.domain.group.exception;

import com.ezel.voza.global.error.BasicException;
import com.ezel.voza.global.error.ErrorCode;
import lombok.Getter;

@Getter
public class NotExistGroupException extends BasicException {

    public NotExistGroupException() {
        super(ErrorCode.NOT_EXIST_GROUP);
    }
}
