package com.ezel.voza.domain.group.exception;

import com.ezel.voza.global.error.BasicException;
import com.ezel.voza.global.error.ErrorCode;
import lombok.Getter;

@Getter
public class LeaderNotOutGroupException extends BasicException {

    public LeaderNotOutGroupException() {
        super(ErrorCode.LEADER_NOT_OUT_GROUP);
    }
}
