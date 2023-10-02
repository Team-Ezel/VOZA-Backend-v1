package com.ezel.voza.domain.chat.exception;

import com.ezel.voza.global.error.BasicException;
import com.ezel.voza.global.error.ErrorCode;
import lombok.Getter;

@Getter
public class NotAllowedFindChatException extends BasicException {

    public NotAllowedFindChatException() {
        super(ErrorCode.NOT_ALLOWED_EXIST_USER);
    }
}
