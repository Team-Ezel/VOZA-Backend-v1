package com.ezel.voza.domain.group.exception;

import com.ezel.voza.global.error.BasicException;
import com.ezel.voza.global.error.ErrorCode;
import lombok.Getter;

@Getter
public class YouNotLeaderException extends BasicException {

    public YouNotLeaderException() {
        super(ErrorCode.YOU_NOT_LEADER);
    }
}
