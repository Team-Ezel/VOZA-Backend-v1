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
public class CalenderDayResponse {

    private Long calender_id;

    private String title;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH-mm-ss")
    private LocalDateTime startDate;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH-mm-ss")
    private LocalDateTime endDate;

    public static CalenderDayResponse toResponse(Calender calender) {

        return CalenderDayResponse.builder()
                .calender_id(calender.getId())
                .title(calender.getTitle())
                .startDate(calender.getStartDate())
                .endDate(calender.getEndDate())
                .build();
    }
}
