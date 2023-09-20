package com.ezel.voza.domain.calender.service;

import com.ezel.voza.domain.calender.presentation.dto.request.GetDateRequest;
import com.ezel.voza.domain.calender.presentation.dto.response.CalenderMonthListResponse;

public interface CalenderMonthListService {

    CalenderMonthListResponse execute(Long id, GetDateRequest getDateRequest);
}
