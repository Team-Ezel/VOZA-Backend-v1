package com.ezel.voza.global.util;

import com.ezel.voza.domain.member.entity.enums.KickOutTime;
import com.ezel.voza.domain.member.exception.InvaildFormatException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class KickOutTimeUtil {

    public static long getSeconds(KickOutTime kickOutTime) {

        final int SECONDS_IN_AN_HOUR = 60 * 60;
        final int SECONDS_IN_A_DAY = 24 * SECONDS_IN_AN_HOUR;

        return switch (kickOutTime) {
            case FIVE_HOUR_STOP -> 5 * SECONDS_IN_AN_HOUR;
            case EIGHT_HOUR_STOP -> 8 * SECONDS_IN_AN_HOUR;
            case TWELVE_HOUR_STOP -> 12 * SECONDS_IN_AN_HOUR;
            case ONE_DAY_STOP -> SECONDS_IN_A_DAY;
            case THREE_DAY_STOP -> 3 * SECONDS_IN_A_DAY;
            case SEVEN_DAY_STOP -> 7 * SECONDS_IN_A_DAY;
            case THIRTY_DAY_STOP -> 30 * SECONDS_IN_A_DAY;
            case PERMANENT_STOP -> Long.MAX_VALUE;
            default -> throw new InvaildFormatException();
        };
    }
}
