package com.ezel.voza.domain.calender.service;

import com.ezel.voza.domain.calender.presentation.dto.response.CalenderDetailResponse;

public interface CalenderDetailService {

    CalenderDetailResponse execute(Long id);
}
