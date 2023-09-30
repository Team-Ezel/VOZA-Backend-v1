package com.ezel.voza.global.util;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

@Component
@AllArgsConstructor
public class DateUtil {

    public static String toTimeAgo(LocalDateTime localDateTime) {
        LocalDateTime now = LocalDateTime.now();
        long[] units = {
                ChronoUnit.YEARS.between(localDateTime, now),
                ChronoUnit.MONTHS.between(localDateTime, now),
                ChronoUnit.DAYS.between(localDateTime, now),
                ChronoUnit.HOURS.between(localDateTime, now),
                ChronoUnit.MINUTES.between(localDateTime, now)
        };

        String[] labels = {"년", "달", "일", "시간", "분"};
        for (int i = 0; i < units.length; i++) {
            if (units[i] > 0) {
                return units[i] + labels[i] + " 전";
            }
        }

        return "방금 전";
    }
}
