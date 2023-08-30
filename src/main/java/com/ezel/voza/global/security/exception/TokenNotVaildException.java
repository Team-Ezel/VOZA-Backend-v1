package com.ezel.voza.global.security.exception;

import com.ezel.voza.global.error.BasicException;
import com.ezel.voza.global.error.ErrorCode;
import lombok.Getter;

@Getter
public class TokenNotVaildException extends BasicException {


    public TokenNotVaildException() {
        super(ErrorCode.TOKEN_NOT_VALID);
    }
}
