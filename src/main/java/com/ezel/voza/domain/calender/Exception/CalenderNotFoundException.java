package com.ezel.voza.domain.calender.Exception;

import com.ezel.voza.global.error.BasicException;
import com.ezel.voza.global.error.ErrorCode;

public class CalenderNotFoundException extends BasicException {

    public CalenderNotFoundException() {
        super(ErrorCode.CALENDER_NOT_FOUND);
    }
}
