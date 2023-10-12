package com.ezel.voza.domain.calender.service;

import com.ezel.voza.domain.calender.presentation.dto.request.UpdateCalenderRequest;

public interface UpdateCalenderService {

    void execute(UpdateCalenderRequest updateCalenderRequest, Long id);
}
