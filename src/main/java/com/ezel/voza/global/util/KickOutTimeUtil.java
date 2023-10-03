package com.ezel.voza.global.util;

import com.ezel.voza.domain.member.entity.enums.KickOutTime;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class KickOutTimeUtil {

    public static long getSeconds(KickOutTime kickOutTime) {
        return switch (kickOutTime) {
            case SEVEN_DAY_STOP -> 7 * 24 * 60 * 60; // 7일
            case THIRTY_DAY_STOP -> 30 * 24 * 60 * 60; // 30일
            case ALL_DAY_STOP -> Long.MAX_VALUE; // 무기한 추방
            default -> throw new IllegalArgumentException("Unsupported KickOutTime: " + kickOutTime);
        };
    }
}
