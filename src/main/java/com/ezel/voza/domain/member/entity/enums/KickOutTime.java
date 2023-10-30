package com.ezel.voza.domain.member.entity.enums;

import com.fasterxml.jackson.annotation.JsonCreator;

public enum KickOutTime {
    FIVE_HOUR_STOP, EIGHT_HOUR_STOP, TWELVE_HOUR_STOP,
    ONE_DAY_STOP, THREE_DAY_STOP, SEVEN_DAY_STOP, THIRTY_DAY_STOP,
    ALL_DAY_STOP;

    @JsonCreator
    public static KickOutTime from(String s) {
        return KickOutTime.valueOf(s.toUpperCase());
    }
}
