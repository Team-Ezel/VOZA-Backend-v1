package com.ezel.voza.domain.calender.service;

import com.ezel.voza.domain.calender.presentation.dto.request.GetDateRequest;
import com.ezel.voza.domain.calender.presentation.dto.response.CalenderDayListResponse;

public interface CalenderDayListService {

    CalenderDayListResponse execute(Long group_id, GetDateRequest getDateRequest);
}
