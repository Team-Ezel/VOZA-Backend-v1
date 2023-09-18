package com.ezel.voza.domain.calender.service;

import com.ezel.voza.domain.calender.presentation.dto.request.CreateCalenderRequest;

public interface CreateCalenderService {

    void execute(CreateCalenderRequest createCalenderRequest, Long id);
}
