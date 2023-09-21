package com.ezel.voza.domain.user.exception;

import com.ezel.voza.global.error.BasicException;
import com.ezel.voza.global.error.ErrorCode;
import lombok.Getter;

@Getter
public class ExistBanGroupException extends BasicException {

    public ExistBanGroupException() {
        super(ErrorCode.EXIST_BAN_GROUP);
    }
}
