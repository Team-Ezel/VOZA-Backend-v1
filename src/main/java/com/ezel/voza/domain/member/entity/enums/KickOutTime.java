package com.ezel.voza.domain.member.entity.enums;

import com.fasterxml.jackson.annotation.JsonCreator;

public enum KickOutTime {
    SEVEN_DAY_STOP, THIRTY_DAY_STOP, ALL_DAY_STOP;

    @JsonCreator
    public static KickOutTime from(String s) {
        return KickOutTime.valueOf(s.toUpperCase());
    }
}
