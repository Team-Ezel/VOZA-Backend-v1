package com.ezel.voza.domain.calender.Exception;

import com.ezel.voza.global.error.BasicException;
import com.ezel.voza.global.error.ErrorCode;

public class CalenderAuthorMismatchException extends BasicException {

    public CalenderAuthorMismatchException() {
        super(ErrorCode.MISMATCH_CALENDER_AUTHOR);
    }
}
