package com.ezel.voza.domain.report.service.impl;

import com.ezel.voza.domain.report.entity.Report;
import com.ezel.voza.domain.report.presentation.dto.response.ListReportResponse;
import com.ezel.voza.domain.report.presentation.dto.response.ReportResponse;
import com.ezel.voza.domain.report.repository.ReportRepository;
import com.ezel.voza.domain.report.service.ListReportService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ListReportServiceImpl implements ListReportService {

    private final ReportRepository reportRepository;

    @Override
    public ListReportResponse execute() {

        List<Report> list = reportRepository.findAll();

        return ListReportResponse.builder()
                .reportResponseList(
                        list.stream()
                                .map(ReportResponse::toResponse)
                                .collect(Collectors.toList())
                )
                .build();
    }
}
