package com.ezel.voza.global.util;

import com.ezel.voza.domain.member.entity.enums.KickOutTime;
import com.ezel.voza.domain.member.exception.InvaildFormatException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class KickOutTimeUtil {

    public static long getSeconds(KickOutTime kickOutTime) {
        return switch (kickOutTime) {
            case FIVE_HOUR_STOP -> 5 * 60 * 60; // 5시간
            case EIGHT_HOUR_STOP -> 8 * 60 * 60; // 8시간
            case TWELVE_HOUR_STOP -> 12 * 60 * 60; // 12시간
            case ONE_DAY_STOP -> 24 * 60 * 60; // 1일
            case THREE_DAY_STOP -> 3 * 24 * 60 * 60; // 3일
            case SEVEN_DAY_STOP -> 7 * 24 * 60 * 60; // 7일
            case THIRTY_DAY_STOP -> 30 * 24 * 60 * 60; // 30일
            case PERMANENT_STOP -> Long.MAX_VALUE; // 무기한 추방
            default -> throw new InvaildFormatException();
        };
    }
}
