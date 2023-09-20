package com.ezel.voza.domain.user.exception;

import com.ezel.voza.global.error.BasicException;
import com.ezel.voza.global.error.ErrorCode;
import lombok.Getter;

@Getter
public class NotBlackUserException extends BasicException {

    public NotBlackUserException() {
        super(ErrorCode.NOT_EXIST_BLACK_USER);
    }
}
