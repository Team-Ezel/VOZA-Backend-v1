package com.ezel.voza.domain.auth.exception;

import com.ezel.voza.global.error.BasicException;
import com.ezel.voza.global.error.ErrorCode;
import lombok.Getter;

@Getter
public class YouBanException extends BasicException {

    public YouBanException() {
        super(ErrorCode.YOU_BANNED);
    }
}
