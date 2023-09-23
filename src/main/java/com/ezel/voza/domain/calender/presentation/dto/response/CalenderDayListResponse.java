package com.ezel.voza.domain.calender.presentation.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
@AllArgsConstructor
public class CalenderDayListResponse {

    private List<CalenderDayResponse> calenderDayResponses;
}
