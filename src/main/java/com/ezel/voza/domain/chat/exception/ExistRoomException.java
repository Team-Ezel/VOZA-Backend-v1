package com.ezel.voza.domain.chat.exception;

import com.ezel.voza.global.error.BasicException;
import com.ezel.voza.global.error.ErrorCode;

public class ExistRoomException extends BasicException {

    public ExistRoomException() {
        super(ErrorCode.EXIST_CHATTING_ROOM);
    }
}
