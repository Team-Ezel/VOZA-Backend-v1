package com.ezel.voza.domain.report.exception;

import com.ezel.voza.global.error.BasicException;
import com.ezel.voza.global.error.ErrorCode;
import lombok.Getter;

@Getter
public class FailedEmailSendException extends BasicException {

    public FailedEmailSendException() {
        super(ErrorCode.SEND_EMAIL_FAILED);
    }
}
