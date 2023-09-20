package com.ezel.voza.domain.calender.presentation.dto.response;

import com.ezel.voza.domain.calender.entity.Calender;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class CalenderMonthResponse {

    private Long id;

    private String startDate;

    private String endDate;

    public static CalenderMonthResponse toResponse(Calender Calender) {

        return CalenderMonthResponse.builder()
                .id(Calender.getId())
                .startDate(Calender.getStartDate())
                .endDate(Calender.getEndDate())
                .build();
    }
}
