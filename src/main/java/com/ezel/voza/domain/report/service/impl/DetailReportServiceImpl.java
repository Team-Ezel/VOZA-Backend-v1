package com.ezel.voza.domain.report.service.impl;

import com.ezel.voza.domain.report.entity.Report;
import com.ezel.voza.domain.report.exception.NotFoundReportException;
import com.ezel.voza.domain.report.presentation.dto.response.DetailReportResponse;
import com.ezel.voza.domain.report.repository.ReportRepository;
import com.ezel.voza.domain.report.service.DetailReportService;
import com.ezel.voza.domain.user.entity.enums.Role;
import com.ezel.voza.domain.user.exception.YouNotAdminException;
import com.ezel.voza.global.annotation.ReadOnlyService;
import com.ezel.voza.global.util.UserUtil;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@ReadOnlyService
public class DetailReportServiceImpl implements DetailReportService {

    private final ReportRepository reportRepository;
    private final UserUtil userUtil;

    @Override
    public DetailReportResponse execute(Long reportId) {

        if (userUtil.currentUser().getRole() != Role.ROLE_ADMIN) {
            throw new YouNotAdminException();
        }

        Report report = reportRepository.findById(reportId)
                .orElseThrow(NotFoundReportException::new);

        return DetailReportResponse.builder()
                .title(report.getTitle())
                .content(report.getContent())
                .name(report.getName())
                .reportType(report.getReportType())
                .createTime(report.getCreateTime()).build();
    }
}
