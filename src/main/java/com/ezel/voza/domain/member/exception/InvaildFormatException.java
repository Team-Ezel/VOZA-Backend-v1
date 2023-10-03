package com.ezel.voza.domain.member.exception;

import com.ezel.voza.global.error.BasicException;
import com.ezel.voza.global.error.ErrorCode;
import lombok.Getter;

@Getter
public class InvaildFormatException extends BasicException {

    public InvaildFormatException() {
        super(ErrorCode.INVALID_FORMAT);
    }
}
