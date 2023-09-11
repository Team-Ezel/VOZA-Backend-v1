package com.ezel.voza.domain.file.exception;

import com.ezel.voza.global.error.BasicException;
import com.ezel.voza.global.error.ErrorCode;

public class FileUploadFailedException extends BasicException {

    public FileUploadFailedException() {
        super(ErrorCode.FILE_UPLOAD_FAIL);
    }
}
