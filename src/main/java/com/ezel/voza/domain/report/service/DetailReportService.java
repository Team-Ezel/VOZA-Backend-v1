package com.ezel.voza.domain.report.service;

import com.ezel.voza.domain.report.presentation.dto.response.DetailReportResponse;

public interface DetailReportService {

    DetailReportResponse execute(Long reportId);
}
