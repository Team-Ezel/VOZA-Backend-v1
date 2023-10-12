package com.ezel.voza.domain.report.service;

import com.ezel.voza.domain.report.presentation.dto.request.SendRefuseRequest;

public interface RefuseReportService {

    void execute(Long reportId, SendRefuseRequest sendRefuseRequest);
}
