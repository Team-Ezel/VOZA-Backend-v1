package com.ezel.voza.domain.member.exception;

import com.ezel.voza.global.error.BasicException;
import com.ezel.voza.global.error.ErrorCode;
import lombok.Getter;

@Getter
public class NotManagerException extends BasicException {

    public NotManagerException() {
        super(ErrorCode.NOT_MANAGER);
    }
}
