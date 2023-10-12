package com.ezel.voza.domain.calender.presentation.dto.response;

import com.ezel.voza.domain.calender.entity.Calender;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
@AllArgsConstructor
public class CalenderMonthResponse {

    private Long calender_id;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH-mm")
    private LocalDateTime startDate;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH-mm")
    private LocalDateTime endDate;

    public static CalenderMonthResponse toResponse(Calender Calender) {

        return CalenderMonthResponse.builder()
                .calender_id(Calender.getId())
                .startDate(Calender.getStartDate())
                .endDate(Calender.getEndDate())
                .build();
    }
}
