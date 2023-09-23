package com.ezel.voza.domain.report.exception;

import com.ezel.voza.global.error.BasicException;
import com.ezel.voza.global.error.ErrorCode;

public class NotFoundReportException extends BasicException {

    public NotFoundReportException() {
        super(ErrorCode.REPORT_NOT_EXIST);
    }
}
