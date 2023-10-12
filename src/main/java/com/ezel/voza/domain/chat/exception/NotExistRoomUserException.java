package com.ezel.voza.domain.chat.exception;

import com.ezel.voza.global.error.BasicException;
import com.ezel.voza.global.error.ErrorCode;
import lombok.Getter;

@Getter
public class NotExistRoomUserException extends BasicException {

    public NotExistRoomUserException() {
        super(ErrorCode.NOT_EXIST_ROOM);
    }
}
