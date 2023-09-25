package com.ezel.voza.domain.group.exception;

import com.ezel.voza.global.error.BasicException;
import com.ezel.voza.global.error.ErrorCode;
import lombok.Getter;

@Getter
public class EnterOnlyCodeException extends BasicException {

    public EnterOnlyCodeException() {
        super(ErrorCode.ENTER_ONLY_CODE);
    }
}
