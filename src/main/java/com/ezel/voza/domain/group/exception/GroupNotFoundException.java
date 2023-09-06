package com.ezel.voza.domain.group.exception;

import com.ezel.voza.global.error.BasicException;
import com.ezel.voza.global.error.ErrorCode;
import lombok.Getter;

@Getter
public class GroupNotFoundException extends BasicException {

    public GroupNotFoundException() {
        super(ErrorCode.GROUP_NOT_FOUND);
    }
}
