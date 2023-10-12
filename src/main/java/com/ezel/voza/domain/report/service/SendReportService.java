package com.ezel.voza.domain.report.service;

import com.ezel.voza.domain.report.presentation.dto.request.CreateReportRequest;

public interface SendReportService {

    void execute(CreateReportRequest createReportRequest);
}
